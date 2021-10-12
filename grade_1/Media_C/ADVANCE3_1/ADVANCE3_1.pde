final float parameter = 0.01;

float xTheta = PI / 6,yTheta = PI / 6;

float cOffset = 300;

PVector pos;

PVector parentPos;

float matrixTheta = 0;
float matrixDTheta = PI/4;
float childTheta = 0;

MyBox[] boxes;
ArrayList<MyBox> lastboxes;

void setup() {
    strokeWeight(1);
    size(480,480,P3D);
    frameRate(60);
    pos = new PVector(0, 0,300);
    parentPos = new PVector(50,0,0);
    boxes = new MyBox[4];
    lastboxes=new ArrayList<MyBox>();
    for(int i = 0; i < boxes.length; i++){
        PVector dPos = new PVector(25*cos(PI*i/4),0,25*sin(PI*i/4));
        MyBox hoge=new MyBox();
        for(int j = 0; j < 4;j++){
            boxes[j]=new MyBox(PVector.mult(dPos,j+1),50-10-j,hoge);
            hoge = boxes[j];
            if(j==3){
                lastboxes.add(hoge);
            }
        }
    }
    rotationCamera(0,0);  
}

void draw() {
    background(127);
    
    camera(pos.x, pos.y,pos.z,0, 0, 0, 0, 1, 0);
    if (mousePressed) {
        rotationCamera( -(pmouseY - mouseY) * parameter,(pmouseX - mouseX) * parameter);
   }
    drawLine(100);

    for(MyBox box : lastboxes){
        box.draw();
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

public class MyBox {
    MyBox node;
    float boxSize;
    public PVector pos;
    public MyBox(PVector pos, float size,MyBox node) {
        this.pos = pos;
        this.boxSize = size;
        this.node = node;
    }
    public MyBox(){
        this.node = new MyBox(new PVector(0,0,0),0,null);
        this.pos = new PVector(0, 0,0);
        float boxSize = 0;
    }
    public void draw(){
        pushMatrix();
        translate(node.pos.x+pos.x, node.pos.y+pos.y,node.pos.z+pos.z);
        drawLine(boxSize*1.5);
        stroke(0);
        box(boxSize);
        popMatrix();
        if(node.pos.x>1){
            node.draw();
        }
    }
}