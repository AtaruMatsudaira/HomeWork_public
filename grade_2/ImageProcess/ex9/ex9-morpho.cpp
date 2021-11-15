#include <iostream>
#include <opencv2/opencv.hpp>

using namespace std;
//画像ファイルの表示
void readImage(string filename = "sample.jpg") {
    cv::Mat inputImage = cv::imread(filename); //画像の読み込み
    cv::Mat grayImage;
    cv::Mat resultImage, pastResultImage;
    if(inputImage.empty()) //画像が正しく読み込めたのかを確認
    {
        cout << "画像ファイルを読み込めませんでした．" << endl;
        return;
    }
    cv::cvtColor(inputImage, grayImage, cv::COLOR_BGR2GRAY);
    cv::imshow("gray", grayImage);
    cv::imshow("processImage", grayImage);
    pastResultImage = grayImage.clone();
    while(true) {
        switch(cv::waitKey()) {
        case 'e':
            pastResultImage = grayImage.clone();
            cv::erode(grayImage, grayImage, cv::Mat());
            break;
        case 'd':
            pastResultImage = grayImage.clone();
            cv::dilate(grayImage, grayImage, cv::Mat());
            break;
        case 'b':
            grayImage = pastResultImage.clone();
            break;
        case 'q':
            return;
            break;
        }
        cv::imshow("gray", grayImage);
        cv::imshow("past", pastResultImage);
    }
}
int main(int argc, char **argv) {
    if(argc < 2) {
        readImage("sample.jpg"); //引数省略時のdefault設定
    } else {
        readImage(argv[1]);
    }
    return 0;
}
