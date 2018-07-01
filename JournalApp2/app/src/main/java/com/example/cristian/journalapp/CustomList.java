package com.example.cristian.journalapp;

public class CustomList {
    public String mVersionName;
    // Drawable resource ID
    private int mImageResourceId;

    /*
     * Create a new AndroidFlavor object.
     *
     * @param vName is the name of the Android version (e.g. Gingerbread)
     * @param vNumber is the corresponding Android version number (e.g. 2.3-2.7)
     * @param image is drawable reference ID that corresponds to the Android version
     * */
    public CustomList(String vName, int imageResourceId)
    {
        mVersionName = vName;
        mImageResourceId = imageResourceId;
    }

    /**
     * Get the name of the Android version
     */
    public String getVersionName() {
        return mVersionName;
    }

    public int getImageResourceId() {
        return mImageResourceId;
    }


}
