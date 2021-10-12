#include <iostream>
#include <opencv2/opencv.hpp>

using namespace std;

//カメラ入力の表示
void readImage(string filename) {
    cout << "kの変更 + w | s -" << endl;
    int laplacin_size = 3;
    double canny_t1 = 100, canny_t2 = 200;
    float k = 1.5;
    cv::Mat laplacian, canny, marge;
    cv::Mat inputImage = cv::imread(filename);
    while(true) {
        cv::Mat kernel =
            (cv::Mat_<float>(3, 3) << -(k / 9), -(k / 9), -(k / 9), -(k / 9),
             1 + k * 8 / 9, -(k / 9), -(k / 9), -(k / 9), -(k / 9));
        cv::filter2D(inputImage, marge, -1, kernel);
        cv::imshow("input", inputImage); //画像の表示
        cv::imshow("marge", marge);
        switch(cv::waitKey()) {
        case 'w':
            k += 0.1;
            cout << "k = " << k << endl;
            break;
        case 'e':
            k -= 0.1;
            if(k <= 0)
                k = 0.1;
            cout << "k = " << k << endl;
            break;
        case 'q':
            return;
            break;
        }
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