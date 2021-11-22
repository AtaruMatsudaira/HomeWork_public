#include <iostream>
#include <opencv2/opencv.hpp>

using namespace std;

//画像ファイルの表示
void ex10_posterize64(string filename = "sample.jpg") {
    cv::Mat inputImage = cv::imread(filename); //画像の読み込み
    cv::Mat outputImage;
    if(inputImage.empty()) //画像が正しく読み込めたのかを確認
    {
        cout << "画像ファイルを読み込めませんでした．" << endl;
        return;
    }
    cv::Mat rgbArray[3];
    cv::split(inputImage, rgbArray);
    for(int i = 0; i < 3; i++) {
        for(int x = 0; x < inputImage.cols; x++) {
            for(int y = 0; y < inputImage.rows; y++) {
                int color = rgbArray[i].ptr(y)[x] / 64;
                rgbArray[i].ptr(y)[x] = color * 64;
            }
        }
    }
    cv::merge(rgbArray, 3, outputImage);
    cv::imshow("Output", outputImage);
    cv::waitKey(); //入力があるまで待機
}

int main(int argc, char **argv) {
    if(argc < 2) {
        ex10_posterize64("sample.jpg"); //引数省略時のdefault設定
    } else {
        ex10_posterize64(argv[1]);
    }
    return 0;
}
