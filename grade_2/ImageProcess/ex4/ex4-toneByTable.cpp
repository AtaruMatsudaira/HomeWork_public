#include <iostream>
#include <opencv2/opencv.hpp>

using namespace std;

void readImage(string filename = "sample.jpg") {
    cv::Mat inputImage = cv::imread(filename); //画像の読み込み
    cv::Mat outputImage;
    if(inputImage.empty()) //画像が正しく読み込めたのかを確認
    {
        cout << "画像ファイルを読み込めませんでした．" << endl;
        return;
    }
    if(inputImage.cols == inputImage.rows) {
        cv::resize(inputImage, inputImage, cv::Size(560, 560));
    } else if(inputImage.cols > inputImage.rows) {
        cv::resize(inputImage, inputImage, cv::Size(640, 480));
    } else {
        cv::resize(inputImage, inputImage, cv::Size(480, 640));
    }
    cv::imshow("Input", inputImage);
    cv::cvtColor(inputImage, inputImage, cv::COLOR_BGR2GRAY);
    cv::imshow("Gray", inputImage);
    cv::Mat dst(cv::Size(inputImage.cols, inputImage.rows), CV_8UC3);
    for(int j = 0; j < inputImage.rows; j++) {
        // y ⾏⽬の先頭画素へのポインタを取得
        uchar *p = inputImage.ptr(j);
        uchar *q = dst.ptr(j);
        for(int i = 0; i < inputImage.cols; i++) {
            int blue = i * 3;
            int green = i * 3 + 1;
            int red = i * 3 + 2;

            // blue
            if(p[i] < 64) {
                q[blue] = 255;
            } else if(p[blue] < 128) {
                q[blue] = 255 - 4 * p[i];
            } else {
                q[blue] = 0;
            }
            // green
            if(p[i] < 64) {
                q[green] = 4 * p[i];
            } else if(p[i] < 192) {
                q[green] = 255;
            } else {
                q[green] = 255 - (p[i] - 192) * 4;
            }

            // red
            if(p[i] < 128) {
                q[red] = 0;
            } else if(p[i] < 192) {
                q[red] = 4 * (p[i] - 128);
            } else {
                q[red] = 255;
            }
        }
    }
    cv::imshow("Output", dst);
    cv::waitKey(); //入力があるまで待機
}
int main(int argc, char **argv) {
    if(argc < 2) {
        readImage("sample.jpg"); //引数省略時のdefault設定
    } else {
        readImage(argv[1]);
    }
    return 0;
}
