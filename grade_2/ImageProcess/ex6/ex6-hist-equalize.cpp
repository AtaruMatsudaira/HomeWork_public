#include <iostream>
#include <opencv2/opencv.hpp>
#include <string>

using namespace std;

cv::Mat getHistEqualize(cv::Mat src) {

    // ルックアップテーブル
    // 今回は1つのルックアップテーブルの中でヒストグラムや累積ヒストグラムの計算を完結させる
    int LUT[256] = {};

    // (1) 画素値のヒストグラムを算出
    for(int i = 0; i < src.rows; i++)
        for(int j = 0; j < src.cols; j++)
            LUT[src.ptr(i, j)[0]]++;

    // (2) (1)のヒストグラムから累積ヒストグラムを算出
    for(int i = 1; i < 256; i++)
        LUT[i] += LUT[i - 1];

    // (3) 累積ヒストグラムから階調変換用LUTを作成
    for(int i = 0; i < 256; i++)
        LUT[i] = LUT[i] * 255.0 / LUT[255];

    // (4) 画像走査内でLUTを用いて階調変換を行う
    cv::Mat result = cv::Mat(src.size(), CV_8UC1);
    for(int i = 0; i < result.rows; i++) {
        for(int j = 0; j < result.cols; j++) {
            result.ptr(i, j)[0] = LUT[src.ptr(i, j)[0]];
        }
    }
    return result;
}

// グレースケール画像のヒストグラム平坦化によるコントラスト調整
void HistEqualize(char *filename) {
    cv::Mat src = cv::imread(filename);
    cv::Mat src_hsv;
    cv::imshow("Input", src);
    cv::Mat matArrays_bgr[3], matArrays_hsv[3];
    cv::Mat bgrResult, hsvResult;
    cv::cvtColor(src, src_hsv, cv::COLOR_BGR2HSV);
    cv::split(src, matArrays_bgr);
    for(int i = 0; i < 3; i++) {
        matArrays_bgr[i] = getHistEqualize(matArrays_bgr[i]);
    }
    cv::merge(matArrays_bgr, 3, bgrResult);
    cv::split(src_hsv, matArrays_hsv);
    matArrays_hsv[2] = getHistEqualize(matArrays_hsv[2]);
    cv::merge(matArrays_hsv, 3, hsvResult);
    cv::cvtColor(hsvResult, hsvResult, cv::COLOR_HSV2BGR);
    // 結果を表示する
    cv::imshow("Input", src);
    cv::imshow("BGR", bgrResult);
    cv::imshow("HSV", hsvResult);
    cv::waitKey();
}

int main(int argc, char *argv[]) {
    if(argc < 2)
        HistEqualize("sample.jpg"); // default画像
    else
        HistEqualize(argv[1]);
    return 0;
}