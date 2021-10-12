final float parameter = 0.01;

float xTheta = PI / 6,yTheta = PI / 6;

float cOffset = 300;

PVector pos;
PShape plane;
PShape car;
PImage roadTexture;
PVector carPos;
float planeWidth = 200, planeHeight = 200;
float roadV=0;

void setup() {
    strokeWeight(1);
    size(480,480,P3D);
    frameRate(60);
    pos = new PVector(0, 0,300);
    rotationCamera(0,0);
    roadTexture = loadImage("road.png");
    createPlane();
    car = loadShape("car.obj");
    carPos = new PVector(0,0,0);
}
void draw() {
    background(0,0,127);
    
    camera(pos.x, pos.y,pos.z,0, 0, 0, 0, 1, 0);
    if (mousePressed) {
        rotationCamera( -(pmouseY - mouseY) * parameter,(pmouseX - mouseX) * parameter);
}
    drawLine(100);
    
    shape(plane);
    createPlane();
    pushMatrix();
    scale(12.5,-12.5,-12.5);
    translate(carPos.x,carPos.y,carPos.z);
    shape(car);
    popMatrix();
}

void keyPressed() {
    if(key == CODED){
        if(keyCode == LEFT){
            carPos.x-=0.5;
        }
        else if(keyCode ==RIGHT){
            carPos.x+=0.5;
        }
    }    
}

void mouseWheel(MouseEvent event) {
    float  f  =  event.getAmount();
    f *=  30;
    if (f!= 0 &&  cOffset + f > 0) {
        cOffset += f;
        rotationCamera(0,0);
    }
}

void rotationCamera(float dXtheta, float dYtheta) {
    float x = 0,y = 0,z = cOffset;
    if ( -0.9 < xTheta + dXtheta)
    {
        if (dXtheta + xTheta < 0.9) {
            xTheta += dXtheta;
        } else{
            xTheta = 0.90;
        }
} else{
        xTheta =-  0.90;
}
    yTheta += dYtheta;
    y = y * cos(xTheta) - z * sin(xTheta);
    z = y * sin(xTheta) + z * cos(xTheta);
    x = x * cos(yTheta) + z * sin(yTheta);
    z =-  x * sin(yTheta) + z * cos(yTheta);
    pos.set(x,y,z);    
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
    float texW = 200,texH = 200;
    float dTexW = texW / planeWidth,dTexH = texH / planeWidth;
    plane = createShape();
    plane.beginShape(QUAD);
    plane.texture(roadTexture);
    plane.fill(255);
    plane.noStroke();
    plane.vertex( - planeWidth / 2,0, - planeHeight / 2,0,roadV);
    plane.vertex(planeWidth / 2,0, - planeHeight / 2,texW ,roadV);
    plane.vertex(planeWidth / 2,0,planeHeight / 2,texW ,(100 + roadV));
    plane.vertex( - planeWidth / 2,0,planeHeight / 2,0,(100 +roadV));
    plane.endShape(CLOSE);
    roadV+=1;
    roadV%=100;
}