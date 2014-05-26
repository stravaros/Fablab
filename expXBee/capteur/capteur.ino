int ok;
int err;
int etat;
int etatSuivantReception;
int nbreReception;
int addr;
int trameR = 0;
int trameW = 0;

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
 etat = 0;
 addr = 3;
 pinMode(8,OUTPUT);
 
}

void loop(){
  char buffer[100];
  int puiss[12];
  
  switch(etat){
    
      case -1:
        if(trameAvailable()){
          //Serial.write("trame recue");
          //Serial.write("\nReception terminé\n");
          etat = etatSuivantReception;
        }
        break;
 
    /* case 0:
      Serial.write("+++");
      etat = -1;
      etatSuivantReception = 1;
      nbreReception = 3;
      digitalWrite(8,HIGH);
      break;*/
      
     case 0:
       Serial.write("+++");
       waitOK();
       Serial.write("ATCH 0C\r");
       waitOK();
       Serial.write("ATID 3332\r");
       waitOK();
       Serial.write("ATPL 3\r");
       waitOK();
       Serial.write("ATPL 4\r");
       waitOK();
      //A mettre, demande n capteur 
       Serial.write("ATCN\r");
       waitOK();
      //Serial.write("GO");
       etat = -1;
       etatSuivantReception = 2;
       nbreReception = 2;
       break;
     
     case 2 :
       //Serial.write("etat2\n");
      // Serial.readBytes(buffer,100);
       //Serial.write(buffer[1]);
       if(bufferTrame[trameR].code=='A'){
         envoyerTrame(bufferTrame[trameR].src,'B',0,0);
         trameR = (trameR+1)%40;
         etat = -1;
         etatSuivantReception = 2;
         nbreReception = 2;
       } else { 
         trameR = (trameR+1)%40;
       }
       break;
  }
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
    etat = 0;
    return(1);
  }
}
 
 
void recevoirTrame(){
  while(Serial.available()>=5){
    bufferTrame[trameW].dest = Serial.read();
    bufferTrame[trameW].src = Serial.read();
    bufferTrame[trameW].code = Serial.read();
    bufferTrame[trameW].m1 = Serial.read();
    bufferTrame[trameW].m2 = Serial.read();
    if(bufferTrame[trameW].dest == addr){
      trameW = (trameW+1)%40;
    }
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
