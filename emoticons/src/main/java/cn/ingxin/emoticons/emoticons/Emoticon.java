package cn.ingxin.emoticons.emoticons;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 定义表情实体
 */
public class Emoticon implements Parcelable {

    //表情字符码，例如[生气]，[哼哼],即key值
    public String key;
    //表情图片
    public int imageRes;

    public Emoticon(String key, int imageRes) {
        this.key = key;
        this.imageRes = imageRes;
    }

    //-----------------Parcelable----------------

    protected Emoticon(Parcel in) {
        imageRes = in.readInt();
        key = in.readString();
    }

    public static final Creator<Emoticon> CREATOR = new Creator<Emoticon>() {
        @Override
        public Emoticon createFromParcel(Parcel in) {
            return new Emoticon(in);
        }

        @Override
        public Emoticon[] newArray(int size) {
            return new Emoticon[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(imageRes);
        parcel.writeString(key);
    }
}
