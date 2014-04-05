int ok;
int err;
int etat;
int etatSuivantReception;
int nbreReception;
int numCapteur;

void setup(){
 
 Serial.begin(9600);
 etat = 0;
 numCapteur = 0;
 pinMode(8,OUTPUT);
 
}

void loop(){
  char buffer[100];
  int puiss[12];
  
  switch(etat){
    
      case -1:
        if(Serial.available()<nbreReception){
          etat = -1;
        } else {
          etat = etatSuivantReception;
        }
        break;
 
     case 0:
      Serial.write("+++");
      etat = -1;
      etatSuivantReception = 1;
      nbreReception = 3;
      digitalWrite(8,HIGH);
      break;
      
     case 1:
       Serial.readBytes(buffer,3);
       if(buffer[0]== 'O' && buffer[1]=='K' && buffer[3] == 0x0D){
         //potentiel config xbee à faire ici
           digitalWrite(8,LOW);
           etat = -1;
           etatSuivantReception = 2;
           nbreReception = 2;
       } else {
           //resetSerial();
           etat = 0;
       }
       break;
     
     case 2 :
       Serial.readBytes(buffer,2);
       if(buffer[1]==0xAA){
         Serial.write(numCapteur);
         Serial.write(0xAB);
         etat = -1;
         etatSuivantReception = 2;
         nbreReception = 2;
       } else { 
         etat = 0;
       }
       break;
  }
}
  
