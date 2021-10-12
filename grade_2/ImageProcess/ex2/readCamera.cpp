#include <iostream>
#include <opencv2/opencv.hpp>

using namespace std;

//カメラ入力の表示
void readCamera() {
    cv::VideoCapture cap(1); //カメラ入力の読み込み

    if(cap.isOpened() == false) //カメラ入力の読み込みに失敗したら終了
    {
        cout << "カメラ入力を読み込めませんでした．" << endl;
        return;
    }

    while(true) {
        cv::Mat frame, outputImage;
        cv::Mat matArray[3];
        cap >> frame; //カメラからの画像を取得

        if(frame.empty()) //画像が正しく読み込めたのかを確認
        {
            cout << "カメラ入力のために待機" << endl;
            continue;
        }

        cv::split(frame, matArray);
        // cv::imshow("Blue", matArray[0]);
        // cv::imshow("Green", matArray[1]);
        // cv::imshow("Red", matArray[2]);

        // matArray[1] = ~matArray[1];
        int x, y;
        for(x = 0; x < matArray[1].rows; x++) {
            for(y = 0; y < matArray[1].rows; y++) {
                matArray[1].
            }
        }

        cv::merge(matArray, 3, outputImage);

        cv::imshow("Output", outputImage); //画像の表示

        if(cv::waitKey(33) >= 0) // 33ms待機後、入力があればbreak
            break;
    }
}

int main(int argc, char **argv) {
    readCamera();
    return 0;
}