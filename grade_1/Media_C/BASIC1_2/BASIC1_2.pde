
float theta = 0;
final float dTheta = 0.01;

float yPos  = -100;

void setup() {
    size(480,480,P3D);
    frameRate(60);
    pastMouseY = 0;
}

void draw() {
    background(127);
    camera(100*sin(theta), yPos, 100*cos(theta), 0, 0, 0, 0, 1, 0);
    theta+=dTheta;
    if(mousePressed){
        yPos += (pmouseY-mouseY);
    }
    drawLine();
}

void drawLine() {
    stroke(255, 0, 0);
    line(0, 0, 0, 100, 0, 0);
    stroke(0, 255, 0);
    line(0, 0, 0, 0, 100, 0);
    stroke(0, 0, 255);
    line(0, 0, 0, 0, 0, 100);
}