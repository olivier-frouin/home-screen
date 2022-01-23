#include <Wire.h>
#include <WiFi.h>

#include <WebServer.h>
 static byte address = 88;
 static int count = 0;
 static byte output = 0;

static int co2Level;
static int tvocLevel;
WebServer server(80);

/** An indicator handle the leds (Green, Yellow and Red)
 *  and the thresholds...
 */
class Indicator {
 private:
  byte gChannel;
  byte yChannel;
  byte rChannel;

  int gThreshold;
  int gyThreshold;
  int yThreshold;
  int yrThreshold;
  int rThreshold;

  public:
  Indicator(
    int gChannel, int yChannel, int rChannel,
    int gThreshold, int gyThreshold, int yThreshold, int yrThreshold, int rThreshold
  ) {
    // Store
    this->gChannel = gChannel;
    this->yChannel = yChannel;
    this->rChannel = rChannel;
    this->gThreshold = gThreshold;
    this->gyThreshold = gyThreshold;
    this->yThreshold = yThreshold;
    this->yrThreshold = yrThreshold;
    this->rThreshold = rThreshold;
  }

  void initPins(int gPin, int yPin, int rPin) {
    pinSetUp(gChannel, gPin);
    pinSetUp(yChannel, yPin);
    pinSetUp(rChannel, rPin);
  }

  void setValue(int value) {
    // Green is ON until gyThreshold is reached and decay until yThreshold
    int green = 255 - interpolate(value, gyThreshold, yThreshold);
    ledcWrite(gChannel, green);

    // Yellow goes from 0 to 255 when value goes from gThreshold to gyThreshold
    //        then goes from 255 to 0 when values goes from yrThreshold to rThreshold
    int yellow = interpolate(value, gThreshold, gyThreshold);
    if ( value > yrThreshold ) {
        yellow = interpolate(value, rThreshold, yrThreshold);
    }
    ledcWrite(yChannel, yellow);

    // Red goes from 0 to 255 between yThreshold and yrThreshold
    int red = interpolate(value, yThreshold, yrThreshold);
    ledcWrite(rChannel, red/2); // divide by 2, my red leds are grighter...
  }
  private:
  void pinSetUp(int channel, byte pin) {
    ledcSetup(channel, 200, 8);
    ledcAttachPin(pin, channel);
    ledcWrite(channel, 127);  
  }

  int interpolate(int value, int from, int to) {
    float k = ((float)(value - from))/(to - from);
    return (int)(255 * constrain(k, 0, 1));   
  }
} ;

/**
 * CO2 Thresholds from internet...
 * <400 : Excellent (lower than average)
 * 600 - 800 : Correct indoor
 * 1000 - 1100 Tolerable
 * 5000 : Dangerous !
 */
static Indicator CO2(0, 1, 2, 500, 800, 1000, 1500, 5000);
/**
 * TVOC thresholds
 * <250 : Excellent
 * 250 - 500 : good
 * 500 - 1000 : acceptable
 * 1000 - 3000 : tolerable
 * > 3000 : Dangerous
 */
static Indicator TVOC(3, 4, 5, 250, 500, 1000, 2000, 3000);


int readFromWire() {
  Serial.printf("available %d\n", Wire.available());
  
    int a = Wire.read();    // receive a byte as character
    int b = Wire.read();
    int crc = Wire.read();

    Serial.printf("received %d,%d,%d\n", a,b,crc);         // print the character
    Serial.printf("computing: %d\n", (a*256 + b));
    
  return a*256 + b;
}

void wifiSetup() {
    WiFi.begin("Freebox-684FC1", "cwz6fq43h6qrbs3cq43vh3");
    Serial.println("\nConnecting");

    while(WiFi.status() != WL_CONNECTED){
        Serial.print(".");
        delay(100);
    }

    Serial.println("\nConnected to the WiFi network");
    Serial.print("[+] ESP32 IP : ");
    Serial.println(WiFi.localIP());
}

void handleRoot(){   // Début de la page HTML
  String page = "<!DOCTYPE html>";
    page += "<html lang='fr'>";
    
    page += "<head>";
    page += "    <title>Serveur ESP32</title>";
    page += "    <meta http-equiv='refresh' content='5' name='viewport' content='width=device-width, initial-scale=1' charset='UTF-8'/>";
    page += "    <link rel='stylesheet' href='https://www.w3schools.com/w3css/4/w3.css'>"; 
    page += "</head>";

    page += "<body>";
    page += "    <div class='w3-card w3-blue w3-padding-small w3-jumbo w3-center'>";
    page += "        <p>CO2: "; page += co2Level; page += "</p>";
    page += "    </div>";
    page += "    <div class='w3-card w3-blue w3-padding-small w3-jumbo w3-center'>";
    page += "        <p>TVOC: "; page += tvocLevel; page += "</p>";
    page += "    </div>";


    page += "</body>";
    page += "</html>";  // Fin de la page HTML

    server.setContentLength(page.length());  // Permet l'affichage plus rapide après chaque clic sur les boutons
    server.send(200, "text/html", page);
}
void handleNotFound(){  // Page Not found
  server.send(404, "text/plain","404: Not found");
}
void serverSetup() {
   server.on("/", handleRoot);
    server.onNotFound(handleNotFound);
   server.begin();
}

void setup() {
   Serial.begin(115200);
    delay(200);
    Serial.println("\nI2C Scanner");

  CO2.initPins(15, 2, 4);
  TVOC.initPins(5, 18, 19);

   Wire.begin();

    Wire.beginTransmission(address);
    output = Wire.write(0x20);
    output = Wire.write(0x03);
    Serial.printf("Wrote1 : %d\n", output);
    //output = Wire.write(0x03b);
    //Serial.printf("Wrote2 : %d\n", output);
    output = Wire.endTransmission();
    delay(200);
  
    Serial.printf("Initialized : %d\n", output);


  wifiSetup();
  serverSetup();
}
 
void loop() {

  count = count+1;
  Serial.printf("Loop %d - %d\n", count, address);
  Wire.beginTransmission(address);
  Wire.write(0x20);
  Wire.write(0x08);
  output = Wire.endTransmission(true);
  Serial.printf("Wrote2 : %d\n", output);
  delay(20);
  output = Wire.requestFrom(address, 0x06, true);
  Serial.printf("RequestFrom : %d\n", output);
  
  co2Level = readFromWire();
  tvocLevel = readFromWire();

  Serial.printf("co2=%d, tcov=%d\n", co2Level, tvocLevel);

  if(count < 15) {
    int select = count%6;
    for(int channel=0; channel<6; channel++) {
      ledcWrite(channel, channel == select ? 150 : 20);
    }
  } else {
    CO2.setValue(co2Level);
    TVOC.setValue(tvocLevel);
  }
 

 delay(1000);
 server.handleClient();
}
