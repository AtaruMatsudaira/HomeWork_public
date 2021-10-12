#include <iostream>
#include <opencv2/opencv.hpp>

using namespace std;
//画像ファイルの表示
void readImage(string filename = "sample.jpg") {
    cv::Mat inputImage = cv::imread(filename); //画像の読み込み
    cv::Mat outputImage, hsvImage;
    if(inputImage.empty()) //画像が正しく読み込めたのかを確認
    {
        cout << "画像ファイルを読み込めませんでした．" << endl;
        return;
    }
    cv::Mat roi(inputImage, cv::Rect(inputImage.cols / 2 - 125,
                                     inputImage.rows / 2 - 100, 250, 200));
    cv::imshow("Input", inputImage);
    cv::bitwise_not(roi, roi);
    cv::imshow("roi", roi);
    cv::imshow("Output", inputImage);
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
