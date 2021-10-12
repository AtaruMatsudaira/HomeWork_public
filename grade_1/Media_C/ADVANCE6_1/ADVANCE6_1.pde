final float parameter = 0.01;
float xTheta = PI / 6,yTheta = PI / 6;
float cOffset = 600;

PVector pos;
PShape plane;
float planeWidth = 600, planeHeight = 600;

PShader _shader;

void setup() {
    strokeWeight(1);
    size(480,480,P3D);
    frameRate(60);
    pos = new PVector(0, 0,300);
    rotationCamera(0,0);  
    createPlane();
    _shader = loadShader("FragmentShader.glsl", "VertexShader.glsl");
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
    directionalLight(255, 255, 255, -1, 1, 0);
    shader(_shader);
    drawPlane();
    drawSphere(50);
    drawLine(100);
}

void drawSphere(float r) {
    pushMatrix();
    noStroke();
    fill(255,0,0);
    translate(0, -r,0);
    sphere(r);
    popMatrix();
}

void drawPlane() {
    pushMatrix();
    translate(-planeWidth/4,0,-planeHeight/4);
    scale(1,-1,1);
    shape(plane);
    popMatrix();
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
