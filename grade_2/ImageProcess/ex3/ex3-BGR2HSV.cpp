#include <iostream>
#include <opencv2/opencv.hpp>

using namespace std;
//画像ファイルの表示
void readImage(string filename = "sample.jpg") {
    cv::Mat inputImage = cv::imread(filename); //画像の読み込み
    cv::Mat outputImage, hsvImage;
    cv::Mat matArray[3];
    if(inputImage.empty()) //画像が正しく読み込めたのかを確認
    {
        cout << "画像ファイルを読み込めませんでした．" << endl;
        return;
    }
    cv::imshow("Input", inputImage);
    cv::cvtColor(inputImage, hsvImage, cv::COLOR_BGR2HSV);
    cv::split(hsvImage, matArray);

    cv::imshow("Hue", matArray[0]);
    cv::multiply(matArray[1], 2, matArray[1]);
    cv::imshow("s", matArray[1]);
    cv::imshow("v", matArray[2]);

    cv::merge(matArray, 3, outputImage);
    cv::cvtColor(outputImage, outputImage, cv::COLOR_HSV2BGR);
    cv::imshow("Output", outputImage);
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
