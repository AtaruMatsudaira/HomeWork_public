#include <iostream>
#include <opencv2/opencv.hpp>

using namespace std;
//画像ファイルの表示
void readImage(string filename = "sample.jpg", int kernelSize = 3) {
    cv::Mat inputImage = cv::imread(filename); //画像の読み込み
    cv::Mat medianBlur;
    if(inputImage.empty()) //画像が正しく読み込めたのかを確認
    {
        cout << "画像ファイルを読み込めませんでした．" << endl;
        return;
    }
    if(kernelSize % 2 == 0) {
        cout << "カーネルサイズが偶数だったで-1しました" << endl;
        kernelSize -= 1;
    }
    cv::cvtColor(inputImage, inputImage, cv::COLOR_BGR2GRAY);
    cv::medianBlur(inputImage, medianBlur, kernelSize);
    cv::imshow("Input", inputImage);
    cv::imshow("medianBlur", medianBlur);
    cv::waitKey(); //入力があるまで待機
    return;
}
int main(int argc, char **argv) {
    if(argc < 2) {
        readImage(); //引数省略時のdefault設定
    } else if(argc < 3) {
        readImage(argv[1]);
    } else {
        readImage(argv[1], atoi(argv[2]));
    }
    return 0;
}
