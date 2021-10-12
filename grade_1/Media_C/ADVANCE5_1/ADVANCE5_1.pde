// -6 ~ 6 (x12.5)


final float parameter = 0.01;

float xTheta = PI / 8, yTheta = 0;

float cOffset = 300;

PVector pos;
PShape plane;
PImage roadTexture;
float planeWidth = 300, planeHeight = 2000;
float roadV=0;
boolean isRunning = true;
boolean isStart = true;

Car car;
ArrayList<Enemy> enemyList = new ArrayList<Enemy>();

void setup() {
  strokeWeight(1);
  size(480, 480, P3D);
  frameRate(60);
  pos = new PVector(0, 0, 300);
  rotationCamera(0, 0);
  roadTexture = loadImage("road.png");
  createPlane();
  enemyList = new ArrayList<Enemy>();
  car = new Car("car.obj",0.5);
  roadV =0;
}
void draw() {
  background(137,189,222);
  camera(pos.x, pos.y, pos.z, 0, 0, 0, 0, 1, 0);
  pushMatrix();
  rotateY(PI);
  shape(plane);
  popMatrix();
  createPlane();
  car.draw();
  enemyProcess();
  if(isRunning){
    drawMessage(String.format("%05d",(int)roadV)+"m",8,new PVector(-42.5,-30));
  }else{
    drawMessage("Your Score"+String.format("%05d",(int)roadV)+"m",7,new PVector(-30,-10));
    drawMessage("If you want to replay ",7,new PVector(-30,7));
    drawMessage("press any key",7,new PVector(-25,15));
  }
}

void enemyProcess(){
  if(frameCount%50==0&&isRunning){
    enemyList.add(new Enemy());
  }
  for(Enemy enemy : enemyList){
    enemy.draw();
    if(enemy.pos.z<-300){
      enemyList.remove(enemy);
    }
  }
}

void keyPressed() {
  if (key == CODED&&isRunning) {
    if (keyCode == LEFT) {
      car.pos.x-=0.5;
    } else if (keyCode ==RIGHT) {
      car.pos.x+=0.5;
    }else if (keyCode ==UP) {
      car.pos.z+=0.5;
    }else if (keyCode ==DOWN) {
      car.pos.z-=0.5;
    }
  }
  if(!isRunning) {
    isRunning = true;
    setup();
  }
}

void mouseWheel(MouseEvent event) {
  float  f  =  event.getAmount();
  f *=  30;
  if (f!= 0 &&  cOffset + f > 0) {
    cOffset += f;
    rotationCamera(0, 0);
  }
}

void rotationCamera(float dXtheta, float dYtheta) {
  float x = 0, y = 0, z = cOffset;
  if ( -0.9 < xTheta + dXtheta)
  {
    if (dXtheta + xTheta < 0.9) {
      xTheta += dXtheta;
    } else {
      xTheta = 0.90;
    }
  } else {
    xTheta =-  0.90;
  }
  yTheta += dYtheta;
  y = y * cos(xTheta) - z * sin(xTheta);
  z = y * sin(xTheta) + z * cos(xTheta);
  x = x * cos(yTheta) + z * sin(yTheta);
  z =-  x * sin(yTheta) + z * cos(yTheta);
  pos.set(x, y, z);
}

void drawLine(float l) {
  stroke(255, 0, 0);
  line(0, 0, 0, l, 0, 0);
  stroke(0, 255, 0);
  line(0, 0, 0, 0, l, 0);
  stroke(0, 0, 255);
  line(0, 0, 0, 0, 0, l);
}

void createPlane() {
  plane = new PShape();
  float texW = 200, texH = 200;
  float dTexW = texW / planeWidth, dTexH = texH / planeWidth;
  plane = createShape();
  plane.beginShape(QUAD);
  plane.texture(roadTexture);
  plane.fill(255);
  plane.noStroke();
  plane.vertex( - planeWidth / 4, 0, - planeHeight / 4, 0, roadV%100);
  plane.vertex(planeWidth / 4, 0, - planeHeight  / 4, texW, roadV%100);
  plane.vertex(planeWidth / 4, 0, planeHeight  *3/ 4, texW, (100 + roadV%100));
  plane.vertex( - planeWidth /4, 0, planeHeight  *3/ 4, 0, (100 +roadV%100));
  plane.endShape(CLOSE);
  if(isRunning) roadV+=1;
}

void drawMessage(String message,float textSize,PVector point){
  pushMatrix();
  translate(pos.x,pos.y,pos.z);
  rotateX(xTheta);
  rotateY(yTheta);
  if(isRunning) {
    fill(0);
  } else {
    fill(0,255,0);
  }
  textMode(SHAPE);
  textSize(textSize);
  text(message, point.x, point.y,-75);
  popMatrix();
}

public class CharacterBase {
  PShape model;
  public PVector pos;
  public float speed;
  public PVector scaleVector;
  public CharacterBase(String filename, float speed) {
    model = loadShape(filename);
    this.speed = speed;
    pos = new PVector(0, 0, 0);
    scaleVector =new PVector(12.5,-12.5,-12.5);
  }
  public void draw() {
    pushMatrix();
    scale(scaleVector.x, scaleVector.y, scaleVector.z);
    translate(pos.x, pos.y, pos.z);
    shape(model);
    popMatrix();
  }
}
public class Car extends CharacterBase{
  public Car(String filename, float speed){
    super(filename, speed);
  }
}
public class Enemy extends CharacterBase {
  public Enemy(){
    super("enemy.obj", random(0.2,0.5));
    pos = new PVector(random(-6,6),0,-random(60,100));
    scaleVector.z*=-1;
  }
  @Override
  public void draw(){
    super.draw();
    if(isRunning)pos.z+=speed;
    if(PVector.sub(pos,car.pos).mag()<2){
      isRunning=false;
    }
  }
}
