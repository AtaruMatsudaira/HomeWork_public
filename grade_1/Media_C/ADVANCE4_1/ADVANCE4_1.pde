final float parameter = 0.01;

float xTheta = PI / 6,yTheta = PI / 6;

float cOffset = 300;

PVector pos;

PShape plane;

float lightTheta = 0;

float planeWidth = 150, planeHeight = 150;

ArrayList<RandomLight> lights;

void setup() {
    strokeWeight(1);
    size(480,480,P3D);
    frameRate(60);
    pos = new PVector(0, 0,300);
    rotationCamera(0,0);  
    createPlane();
    lights = new ArrayList<RandomLight>();
    for(int i = 0; i <8;i++){
        lights.add(new RandomLight());
    }
}

void createPlane(){
    plane = createShape();
    plane.beginShape(QUAD);
    plane.fill(255);
    plane.noStroke();
    for(float x = -plane.width/2;x+1 < planeWidth/2;x++){
        for(float y = -plane.height/2; y+1 < planeHeight/2 ; y++){
            plane.vertex(x, 0, y);
            plane.vertex(x, 0, y+1);
            plane.vertex(x+3, 0, y+1);
            plane.vertex(x+3, 0, y);
        }
    }
    plane.endShape(CLOSE);
}

void drawRoom(){
    fill(127);
    pushMatrix();
    translate(-planeWidth/4,0,-planeHeight/4);
    shape(plane);
    popMatrix();
    pushMatrix();
    translate(-planeWidth/4,-planeHeight/2,-planeHeight/4);
    shape(plane);
    popMatrix();

    pushMatrix();
    rotateX(PI/2);
    translate (-planeWidth/4,-planeHeight/4,0);
    shape(plane);
    popMatrix();

    pushMatrix();
    rotateZ(PI/2);
    translate (-planeWidth/2,planeHeight/4,-planeHeight/4);
    shape(plane);
    popMatrix();
}

void draw() {
    background(127);
    
    camera(pos.x, pos.y,pos.z,0, 0, 0, 0, 1, 0);
    if (mousePressed) {
        rotationCamera( -(pmouseY - mouseY) * parameter,(pmouseX - mouseX) * parameter);
   }
    drawLine(100);
    for(RandomLight l : lights){
        l.draw();
    }
    fill(127);
    drawRoom();
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

public class RandomLight{
    PVector position;
    PVector rotation;
    PVector randomColor;
    PVector dRotation;
    public RandomLight(){
        position = PVector.random3D();
        randomColor = new PVector(random(0,255),random(0,255),random(0,255));
        rotation = PVector.random3D().mult(2*PI);
        dRotation = PVector.random3D().mult(0.01);
        println("color:"+randomColor);
    }
    public void draw(){
        pushMatrix();
        rotateX(rotation.x);
        rotateY(rotation.y);
        rotateZ(rotation.z);
        translate (0,planeWidth/4,0);
        spotLight(
            randomColor.x, randomColor.y, randomColor.z,
            0.0,0.0,0.0,
            position.x,position.y,position.z,
            PI/3,200
        );
        popMatrix();
        rotation=rotation.add(dRotation);
    }
}