package com.example.droidcafewsettings;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.AppCompatImageView;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import static org.hamcrest.CoreMatchers.equalTo;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertThat;
import static org.robolectric.Shadows.shadowOf;

import org.junit.Before;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;
import org.robolectric.shadow.api.Shadow;
import org.robolectric.shadows.ShadowActivity;
import org.robolectric.shadows.ShadowIntent;
import org.robolectric.shadows.ShadowToast;

import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

@RunWith(RobolectricTestRunner.class)

public class MainActivityTest {

    private MainActivity activity;
    private OrderActivity activity1;

    @Before
    public void setUp() throws Exception {
        activity = Robolectric.buildActivity(MainActivity.class)
                .create()
                .resume()
                .get();
    }

    @Test
    public void TestToast() {
        assertNotNull(activity);



        // TC1: You ordered a FroYo

        ImageView froyoButton = (ImageView) activity.findViewById(R.id.froyo);
        froyoButton.performClick();
        System.out.println("FroyoClicked");
        assertTrue(froyoButton.performClick());



        System.out.println(ShadowToast.getTextOfLatestToast());
        assertThat(ShadowToast.getTextOfLatestToast(), equalTo("You ordered a FroYo") );



    }

    @Test
    public void TestSavedInfo() {
        assertNotNull(activity);
        //TC2: Checked save data works
        activity1 = Robolectric.buildActivity(OrderActivity.class)
                .create()
                .resume()
                .get();

//        FloatingActionButton CartButton = (FloatingActionButton) activity1.findViewById(R.id.fab);
//        CartButton.performClick();
//        System.out.println("Cart button Clicked");


        EditText FirstName = (EditText) activity1.findViewById(R.id.name_text);
        EditText Address = (EditText) activity1.findViewById(R.id.address_text);
        EditText Phone = (EditText) activity1.findViewById(R.id.phone_text);
        FirstName.setText("Himu");
        Address.setText("77 rakewood");
        Phone.setText("6476096005");

        Button SaveCustomerInfo = (Button) activity1.findViewById(R.id.saveButton);
        SaveCustomerInfo.performClick();
        String Compare = FirstName.getText().toString();

        System.out.println("Save Info Clicked");



        System.out.println(FirstName.getText());

        activity = Robolectric.buildActivity(MainActivity.class)
                .create()
                .resume()
                .get();


        activity1 = Robolectric.buildActivity(OrderActivity.class)
                .create()
                .resume()
                .get();

        EditText FirstNameSaved = (EditText) activity1.findViewById(R.id.name_text);
        EditText AddressSaved = (EditText) activity1.findViewById(R.id.address_text);
        EditText PhoneSaved = (EditText) activity1.findViewById(R.id.phone_text);
        System.out.println(FirstNameSaved.getText());


        assertEquals(FirstName.getText().toString(),FirstNameSaved.getText().toString());
        assertEquals(Address.getText().toString(),AddressSaved.getText().toString());
        assertEquals(Phone.getText().toString(),PhoneSaved.getText().toString());

    }


}
