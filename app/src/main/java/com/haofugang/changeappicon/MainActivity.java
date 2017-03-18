package com.haofugang.changeappicon;

import android.content.ComponentName;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ComponentName defaultComponentName;
    private ComponentName ElevenComponentName;
    private ComponentName TwelveComponentName;
    private ComponentName OldComponentName;
    private PackageManager mPackageManager;
    private Button eleven;
    private Button Twelve;
    private Button old;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        eleven = (Button) findViewById(R.id.eleven);
        Twelve = (Button) findViewById(R.id.Twelve);
        old = (Button) findViewById(R.id.old);

        eleven.setOnClickListener(this);
        Twelve.setOnClickListener(this);
        old.setOnClickListener(this);
        defaultComponentName = getComponentName();
        ElevenComponentName = new ComponentName(getBaseContext(),"com.haofugang.changeappicon.eleven");
        TwelveComponentName = new ComponentName(getBaseContext(),"com.haofugang.changeappicon.Twelve");
        OldComponentName = new ComponentName(getBaseContext(),"com.haofugang.changeappicon.OldIcon");
        mPackageManager = getApplicationContext().getPackageManager();
    }

    //显示应用图标
    private void enableComponent(ComponentName componentName) {
        mPackageManager.setComponentEnabledSetting(componentName,
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP);
    }

    //隐藏应用图标
    private void disableComponent(ComponentName componentName) {
        mPackageManager.setComponentEnabledSetting(componentName,
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.eleven:
                changeIcon(OldComponentName,defaultComponentName,TwelveComponentName,ElevenComponentName);
                break;
            case R.id.Twelve:
                changeIcon(OldComponentName,defaultComponentName,ElevenComponentName,TwelveComponentName);
                break;
            case R.id.old:
                changeIcon(TwelveComponentName,ElevenComponentName,defaultComponentName,OldComponentName);
                break;
        }

    }

    public void changeIcon(ComponentName... myComponentNames) {
        for(int i=0 ;i<myComponentNames.length-1;i++)
        {
            disableComponent(myComponentNames[i]);
        }
        enableComponent(myComponentNames[myComponentNames.length-1]);
    }
}
