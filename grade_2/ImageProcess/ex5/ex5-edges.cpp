#include <iostream>
#include <opencv2/opencv.hpp>

using namespace std;

//カメラ入力の表示
void readCamera(int index = 1) {
    cout << "Laplacianフィルタのフィルタサイズの変更 - w | s +" << endl;
    cout << "Cannyフィルタのヒステリシス処理の第1閾値 - s | d +" << endl;
    cout << "Cannyフィルタのヒステリシス処理の第2閾値 - x | c +" << endl;
    cv::VideoCapture cap(index); //カメラ入力の読み込み
    if(cap.isOpened() == false) //カメラ入力の読み込みに失敗したら終了
    {
        cout << "カメラ入力を読み込めませんでした．" << endl;
        return;
    }
    int laplacin_size = 3;
    double canny_t1 = 100, canny_t2 = 200;
    while(true) {
        cv::Mat frame, gray, laplacian, canny, marge;
        cap >> frame;     //カメラからの画像を取得
        if(frame.empty()) //画像が正しく読み込めたのかを確認
        {
            cout << "カメラ入力のために待機" << endl;
            continue;
        }
        cv::cvtColor(frame, gray, cv::COLOR_BGR2GRAY);
        cv::Laplacian(gray, laplacian, 0, laplacin_size);
        cv::Canny(gray, canny, canny_t1, canny_t2);
        cv::bitwise_or(laplacian, canny, marge);
        cv::imshow("input", gray); //画像の表示
        cv::imshow("laplacian", laplacian);
        cv::imshow("canny", canny);
        cv::imshow("marge", marge);
        switch(cv::waitKey(15)) {
        case 'e':
            laplacin_size += 2;
            if(laplacin_size > 31)
                laplacin_size = 31;
            break;
        case 'w':
            laplacin_size -= 2;
            if(laplacin_size <= 0)
                laplacin_size = 1;
            break;
        case 'd':
            canny_t1 += 5;
            break;
        case 's':
            canny_t1 -= 5;
            break;
        case 'c':
            canny_t2 += 10;
            break;
        case 'x':
            canny_t2 -= 10;
            break;
        case 'q':
            return;
            break;
        }
    }
}

int main(int argc, char **argv) {
    if(argc < 2) {
        readCamera(); //引数省略時のdefault設定
    } else {
        readCamera(stoi(argv[1]));
    }
    return 0;
}