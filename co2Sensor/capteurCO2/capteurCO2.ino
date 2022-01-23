#include <Wire.h>

 static byte address = 88;
 static int count = 0;
 static byte output = 0;

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

void setup() {
   Serial.begin(115200);
    delay(200);
    Serial.println("\nI2C Scanner");

  CO2.initPins(15, 2, 4);
  TVOC.initPins(5, 18, 19);

   Wire.begin();

#ifdef SCAN_FOR_DEVICE
    address = 1;
    while(address<127) {
       Wire.beginTransmission(address);
      output = Wire.endTransmission();
      if (output == 0) {
        Serial.printf("Found %d\n", address);
        //break;
      }
      
      address++;
    }
  
    address = 88;
    Serial.printf("Using %d\n", address);
#endif

    Wire.beginTransmission(address);
    output = Wire.write(0x20);
    output = Wire.write(0x03);
    Serial.printf("Wrote1 : %d\n", output);
    //output = Wire.write(0x03b);
    //Serial.printf("Wrote2 : %d\n", output);
    output = Wire.endTransmission();
    delay(200);
  
    Serial.printf("Initialized : %d\n", output);

#ifdef TEST_THE_LEDS
    while(true) {
    for(int value=500; value<1500; value+=5) {
      CO2.setValue(value);
      delay(10);
    }

    for(int value=250; value<3000; value+=5) {
      TVOC.setValue(value);
      delay(10);
    }
    }
#endif
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
  
  int co2 = readFromWire();
  int tvoc = readFromWire();

  Serial.printf("co2=%d, tcov=%d\n", co2, tvoc);

  if(count < 15) {
    int select = count%6;
    for(int channel=0; channel<6; channel++) {
      ledcWrite(channel, channel == select ? 150 : 20);
    }
  } else {
    CO2.setValue(co2);
    TVOC.setValue(tvoc);
  }
 

 delay(1000);
}
