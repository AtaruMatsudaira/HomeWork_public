final float parameter = 0.01;

float xTheta = PI/6,yTheta = PI/6;

PVector pos;

void setup() {
    size(480,480,P3D);
    frameRate(60);
    pos = new PVector(100, -100,100);
    rotationCamera(0,0);
}

void draw() {
    background(127);
    camera(pos.x, pos.y,pos.z,0, 0, 0, 0, 1, 0);
    if (mousePressed) {
        rotationCamera( -(pmouseY - mouseY)*parameter,(pmouseX - mouseX)*parameter);
    }
    drawLine();
    drawCircle(40);
    frameCount++;

}

void drawCircle(float radius) {
    stroke(0);
    beginShape();
    float theta = 2*PI/180;
    for(int i = 0; i <180;i++){
        vertex(radius*sin(theta*i),0,radius*cos(theta*i));
    }
    endShape(CLOSE);
}

void  mouseWheel(MouseEvent event){
    float  f  =  event.getAmount();

}

void rotationCamera(float dXtheta, float dYtheta) {
    float x = 0,y = 0,z =300;
    if(-0.9<xTheta+dXtheta)
    {
        if(dXtheta+xTheta<0.9){
            xTheta += dXtheta;
        }else{
            xTheta=0.90;
        }
    }else{
        xTheta=-0.90;
    }
    yTheta+=dYtheta;
    
    /*x = x*cos(yTheta)+z*sin(yTheta);
    y = (2*y*cos(xTheta)-x*cos(yTheta+xTheta)+x*cos(yTheta-xTheta)-z*sin(yTheta+xTheta)+z*sin(yTheta-xTheta))/2;
    z = (z*cos(yTheta+xTheta)+z*cos(yTheta-xTheta)+2*y*sin(xTheta)-x*sin(xTheta+yTheta)-x*sin(yTheta-xTheta))/2;
    */
    
    y=y*cos(xTheta)-z*sin(xTheta);
    z=y*sin(xTheta)+z*cos(xTheta);

    
    x=x*cos(yTheta)+z*sin(yTheta);
    z=-x*sin(yTheta)+z*cos(yTheta);
    
    /*
    y = y* cos(xTheta);

    x = x* cos(yTheta);
    z = z*sin(yTheta);
    */
    
    pos.set(x,y,z);
    
}

void drawLine() {
    stroke(255, 0, 0);
    line(0, 0, 0, 100, 0, 0);
    stroke(0, 255, 0);
    line(0, 0, 0, 0, 100, 0);
    stroke(0, 0, 255);
    line(0, 0, 0, 0, 0, 100);
}