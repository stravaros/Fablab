int ok;
int err;
int etat;
int etatreboot;
int etatSuivantReception;
int nbreReception;
int addr;
int numCapteur;
long compt;
int trameR = 0;
int trameW = 0;
int rssi;
boolean aEnvoyer = false;

struct {
  char dest;
  char src;
  char code;
  char m1;
  char m2;
} typedef trame;
//int nbreCapteur;
trame bufferTrame[40];

void setup(){
 
 Serial.begin(9600);
 addr = 2;
 numCapteur = 3;
 
 Serial.write("+++");
       waitOK();
       Serial.write("ATCH 0C\r");
       waitOK();
       Serial.write("ATID 3332\r");
       waitOK();
       Serial.write("ATGT 000A\r");
       waitOK();
       Serial.write("ATPL 4\r");
       waitOK();
        Serial.write("ATCN\r");
        waitOK();
 
}

void loop(){
  int puiss[12];
  
  if(trameAvailable()){
    if(bufferTrame[trameR].code == 'B'){
      B();
    }else if(bufferTrame[trameR].code == 'G' && bufferTrame[trameR].dest == addr){
      C();
    }trameR = (trameR+1)%40; 
  
}
}
  
void B(){
  if(aEnvoyer == false){
  aEnvoyer = true;
  delay(25);
  Serial.write("+++");
  waitOK();
  Serial.write("ATDB\r");
  rssi = waitDB();
  Serial.write("ATCN\r");
  waitOK();
  }
}     
  
void C(){
  aEnvoyer = false;
  envoyerTrame(0,'C',numCapteur,rssi);
}


int waitOK(){
  char buffer[100];
  while(Serial.available()<3);
  Serial.readBytes(buffer,100);
  if(buffer[0]== 'O' && buffer[1]=='K' /*&& buffer[3] == 0x0D*/){
    //Serial.write("OK\n");
    return(0);
  }else{
    //Serial.write("Erreur : reboot\n");
    etat = -1;
    etatreboot = -1;
    etatSuivantReception = 0;
    return(1);
  }
}

int waitDB(){
  char buffer[100];
  while(Serial.available()<3);//5 a tester
  Serial.readBytesUntil(0x0D,buffer,3);
  return(conv(buffer[1])+conv(buffer[0])*16);
}

void recevoirTrame(){
  if(Serial.available()>=5){
    bufferTrame[trameW].dest = Serial.read();
    bufferTrame[trameW].src = Serial.read();
    bufferTrame[trameW].code = Serial.read();
    bufferTrame[trameW].m1 = Serial.read();
    bufferTrame[trameW].m2 = Serial.read();
    if(bufferTrame[trameW].dest == addr || bufferTrame[trameW].dest == 0)
      trameW = (trameW+1)%40;
  }
}

boolean trameAvailable(){
  recevoirTrame();
  return(trameW!=trameR);
}

void envoyerTrame(char dest, char code, char m1, char m2){
  Serial.write(dest);
  Serial.write(addr);
  Serial.write(code);
  Serial.write(m1);
  Serial.write(m2);
  Serial.flush();
}

int conv(char a){
  switch(a){
    case '1':
      return 1;
    case '2':
      return 2;
    case '3':
      return 3;
    case '4':
      return 4;
    case '5':
      return 5;
    case '6':
      return 6;
    case '7':
      return 7;
    case '8':
      return 8;
    case '9':
      return 9;
    case '0':
      return 0;
    case 'A':
      return 10;
    case 'B':
      return 11;
    case 'C':
      return 12;
    case 'D':
      return 13;
    case 'E':
      return 14;
    case 'F':
      return 15;
    default:
      return 0;
  }
}
      
  
