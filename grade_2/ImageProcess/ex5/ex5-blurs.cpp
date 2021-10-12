#include <iostream>
#include <opencv2/opencv.hpp>

using namespace std;
//画像ファイルの表示
void readImage(string filename = "sample.jpg") {
    cv::Mat inputImage = cv::imread(filename); //画像の読み込み
    cv::Mat blur1, blur2, gaussianBulr1, gaussianBulr2;
    if(inputImage.empty()) //画像が正しく読み込めたのかを確認
    {
        cout << "画像ファイルを読み込めませんでした．" << endl;
        return;
    }
    cv::blur(inputImage, blur1, cv::Size(3, 3));
    cv::blur(inputImage, blur2, cv::Size(7, 7));
    cv::GaussianBlur(inputImage, gaussianBulr1, cv::Size(3, 3), 2, 0);
    cv::GaussianBlur(inputImage, gaussianBulr2, cv::Size(7, 7), 2, 0);
    cv::imshow("Input", inputImage);
    cv::imshow("bluer ( k = 3 )", blur1);
    cv::imshow("bluer ( k = 7 )", blur2);
    cv::imshow("gausssianBuler ( k = 3 )", gaussianBulr1);
    cv::imshow("gausssianBuler ( k = 7 )", gaussianBulr2);
    cv::waitKey(); //入力があるまで待機
    return;
}
int main(int argc, char **argv) {
    if(argc < 2) {
        readImage("sample.jpg"); //引数省略時のdefault設定
    } else {
        readImage(argv[1]);
    }
    return 0;
}
