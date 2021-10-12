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
    cv::imshow("Input", inputImage);
    cv::cvtColor(inputImage, inputImage, cv::COLOR_BGR2GRAY);
    cv::Mat dst(cv::Size(inputImage.cols, inputImage.rows), CV_8UC1);
    for(int j = 0; j < inputImage.rows; j++) {
        // y ⾏⽬の先頭画素へのポインタを取得
        uchar *p = inputImage.ptr(j);
        uchar *q = dst.ptr(j);
        for(int i = 0; i < inputImage.cols; i++) {
            // j⾏⽬,i列⽬の画素の処理
            if(p[i] <= 85)
                q[i] = 2 * p[i]; // x=p[i],y=q[i]
            else
                q[i] = 0.5 * p[i] + 128;
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
