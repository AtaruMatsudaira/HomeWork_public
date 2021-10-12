#include <iostream>
#include <opencv2/opencv.hpp>

using namespace std;
//画像ファイルの表示
void readImage(string filename = "sample.jpg") {
    cv::Mat inputImage = cv::imread(filename); //画像の読み込み
    cv::Mat outputImage;
    cv::Mat matArray[3];
    if(inputImage.empty()) //画像が正しく読み込めたのかを確認
    {
        cout << "画像ファイルを読み込めませんでした．" << endl;
        return;
    }
    cv::imshow("Input", inputImage);
    cv::split(inputImage, matArray);
    cv::Mat temp = matArray[1];
    matArray[1] = ~matArray[2];
    matArray[2] = ~matArray[0];
    matArray[0] = ~temp;

    cv::merge(matArray, 3, outputImage);
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
