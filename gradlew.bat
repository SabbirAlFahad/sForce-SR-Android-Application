<?xml version="1.0" encoding="utf-8"?>

<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:orientation="vertical"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:padding="7dp"
    android:background="@color/colorPrimaryDark"
    android:gravity="center">

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="160dp"
        android:orientation="vertical">

        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="0dp"
            android:layout_weight="20"
            android:orientation="horizontal"
            android:background="@color/colorWhite">

            <TextView
                android:id="@+id/view_tname"
                android:text="TENDER ID"
                android:textSize="12sp"
                android:textStyle="bold"
                android:textColor="@color/colorPink"
                android:gravity="center"
                android:layout_width="0dp"
                android:background="@drawable/edit_text_blue"
                android:layout_height="match_parent"
                android:layout_weight="30"/>


            <TextView
                android:id="@+id/show_tname"
                android:textColor="@color/colorPink"
                android:textSize="10sp"
                android:gravity="center"
                android:background="@drawable/edit_text_blue"
                android:layout_width="0dp"
                android:layout_height="match_parent"
                android:layout_weight="70"/>

        </LinearLayout>

        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="0dp"
            android:layout_weight="20"
            android:orientation="horizontal"
            android:background="@color/colorWhite">

            <TextView
                android:id="@+id/view_expcname"
                android:text="COMPANY NAME"
                android:textSize="10sp"
                android:textStyle="bold"
                android:textColor="@color/colorPink"
                android:gravity="center"
                androi