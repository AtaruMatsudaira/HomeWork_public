final float parameter = 0.01;

float xTheta = PI / 6,yTheta = PI / 6;

float cOffset = 300;

PVector pos;

PVector parentPos;

float matrixTheta = 0;
float matrixDTheta = PI/4;
float childTheta = 0;

void setup() {
    strokeWeight(1);
    size(480,480,P3D);
    frameRate(60);
    pos = new PVector(0, 0,300);
    parentPos = new PVector(50,0,0);
    rotationCamera(0,0);
}

void draw() {
    background(127);
    
    camera(pos.x, pos.y,pos.z,0, 0, 0, 0, 1, 0);
    if (mousePressed) {
        rotationCamera( -(pmouseY - mouseY) * parameter,(pmouseX - mouseX) * parameter);
   }
    drawLine(100);
    
    rotate(radians(matrixTheta), 0, 1, 0);
    matrixTheta+=matrixDTheta;

    pushMatrix();
    translate(parentPos.x, parentPos.y,parentPos.z);
    sphereandaxis(25);
    

    rotate(radians(childTheta),0,1,0);
    childTheta+=2*matrixDTheta;

  
    translate(40,0,0);
    sphereandaxis(5);
    popMatrix();
}

void sphereandaxis(float r)
{
    drawLine(r*1.5);
    stroke(0, 0, 0);
    sphere(r);
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
