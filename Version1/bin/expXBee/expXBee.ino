int level[4];
int ok;
int err;
int firstExec = 1;

void setup(){
  level[0] = 2;
  pinMode(level[0],OUTPUT);
  level[1] = 3;
  pinMode(level[1],OUTPUT);
  level[2] = 4;
  pinMode(level[2],OUTPUT);
  level[3] = 5;
  pinMode(level[3],OUTPUT);
  
  ok = 6;
  pinMode(ok,OUTPUT);
  err = 7;
  pinMode(err,OUTPUT);
 
   //test leds
  digitalWrite(level[0],HIGH);
  digitalWrite(level[1],HIGH);
  digitalWrite(level[2],HIGH);
  digitalWrite(level[3],HIGH);
  digitalWrite(ok,HIGH);
  digitalWrite(err,HIGH);
  delay(1000);
  digitalWrite(level[0],LOW);
  digitalWrite(level[1],LOW);
  digitalWrite(level[2],LOW);
  digitalWrite(level[3],LOW);
  digitalWrite(ok,LOW);
  digitalWrite(err,LOW);
 
 Serial.begin(9600);
 
}

void loop(){
  char buffer[100];
  int puiss[12];
  Serial.write("+++");
  while(Serial.available()<3){
   // Serial.write("att\n");
  }
  Serial.readBytes(buffer, 3);
  
  //debug
  /*Serial.write("recu\n");
  Serial.write("buffer[0] = ");
  Serial.write(buffer[0]);
  Serial.write("\n");
  Serial.write("buffer[1] = ");
  Serial.write(buffer[1]);
  Serial.write("\n");
  Serial.write("buffer[2] = ");
  Serial.write(buffer[2]);
  Serial.write("\n");*/
  
  if(buffer[0]=='O' && buffer[1] =='K'){
    digitalWrite(ok,HIGH);
    digitalWrite(err,LOW);
    //on continue
    Serial.write("ATED");
    Serial.write(0x0D);
    //attente
    while(Serial.available()<3){}
    Serial.write("recu");
    Serial.readBytes(buffer, 35);
    
    for(int i=0; i<12; i++){
      puiss[i]=parse(buffer[i*3])*16+parse(buffer[i*3+1]);
      Serial.write("puiss[i] = ");
      Serial.print(puiss[i]);
      Serial.write("\n");
    }
    
    
    
    
  }else{
    digitalWrite(ok,LOW);
    digitalWrite(err,HIGH);
  }
  while(1);
}

  
  
int parse(char c){
  if(c=='0')
    return 0;
    if(c=='1')
    return 1;
    if(c=='2')
    return 2;
    if(c=='3')
    return 3;
    if(c=='4')
    return 4;
    if(c=='5')
    return 5;
    if(c=='6')
    return 6;
    if(c=='7')
    return 7;
    if(c=='8')
    return 8;
    if(c=='9')
    return 9;
    if(c=='A')
    return 10;
    if(c=='B')
    return 11;
    if(c=='C')
    return 12;
    if(c=='D')
    return 13;
    if(c=='E')
    return 14;
    if(c=='F')
    return 15;
}
  
