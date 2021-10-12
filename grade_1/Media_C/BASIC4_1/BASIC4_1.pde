PShape yuka;

float lightTheta = 0;

float planeWidth = 300, planeHeight = 300;

void setup() {
    strokeWeight(1);
    size(480,480,P3D);
    generateYuka();
}

void draw() {
    background(127);
    camera(100,-100,100,0, 0, 0, 0, 1, 0);
    float y=-20;
    float r = 40;
    pointLight(255, 0, 0, r*cos(lightTheta), y,r*sin(lightTheta));
    pointLight(0, 255, 0, r*cos(lightTheta+2*PI/3), y,r*sin(lightTheta+2*PI/3));
    pointLight(0, 0, 255, r*cos(lightTheta+4*PI/3), y,r*sin(lightTheta+4*PI/3));
    lightTheta += 0.01;pushMatrix();
    translate(-planeWidth/4,0,-planeHeight/4);
    shape(yuka);
    popMatrix();
}

void generateYuka(){
    yuka = createShape();
    yuka.beginShape(QUAD);
    yuka.fill(255);
    yuka.noStroke();
    for(float x = -yuka.width/2;x < planeWidth/2;x++){
        for(float y = -yuka.height/2; y < planeHeight/2 ; y++){
            yuka.vertex(x, 0, y);
            yuka.vertex(x, 0, y+20);
            yuka.vertex(x+20, 0, y+20);
            yuka.vertex(x+20, 0, y);
        }
    }
    yuka.endShape(CLOSE);
}