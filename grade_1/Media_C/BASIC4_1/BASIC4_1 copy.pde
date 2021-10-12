final float parameter = 0.01;

float xTheta = PI / 6,yTheta = PI / 6;

float cOffset = 300;

PVector pos;

PShape plane;

float lightTheta = 0;

float planeWidth = 600, planeHeight = 600;

void setup() {
    strokeWeight(1);
    size(480,480,P3D);
    frameRate(60);
    pos = new PVector(0, 0,300);
    rotationCamera(0,0);  
    createPlane();
}

void createPlane(){
    plane = createShape();
    plane.beginShape(QUAD);
    plane.fill(255);
    plane.noStroke();
    for(float x = -plane.width/2;x < planeWidth/2;x++){
        for(float y = -plane.height/2; y < planeHeight/2 ; y++){
            plane.vertex(x, 0, y);
            plane.vertex(x, 0, y+3);
            plane.vertex(x+3, 0, y+3);
            plane.vertex(x+3, 0, y);
        }
    }
    plane.endShape(CLOSE);
}

void draw() {
    background(127);
    
    camera(pos.x, pos.y,pos.z,0, 0, 0, 0, 1, 0);
    if (mousePressed) {
        rotationCamera( -(pmouseY - mouseY) * parameter,(pmouseX - mouseX) * parameter);
   }
    drawLine(100);
    tripleLight();
    pushMatrix();
    translate(-planeWidth/4,0,-planeHeight/4);
    shape(plane);
    popMatrix();
}

void tripleLight() {
    float y=-20;
    float r = 50;
    pointLight(255, 0, 0, r*cos(lightTheta), y,r*sin(lightTheta));
    pointLight(0, 255, 0, r*cos(lightTheta+2*PI/3), y,r*sin(lightTheta+2*PI/3));
    pointLight(0, 0, 255, r*cos(lightTheta+4*PI/3), y,r*sin(lightTheta+4*PI/3));
    lightTheta += 0.02;
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
    if ( - 0.9 < xTheta + dXtheta)
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
