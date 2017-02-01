package com.io7m.examples.jp4da;

import com.jogamp.newt.event.MonitorEvent;
import com.jogamp.newt.event.MonitorModeListener;
import com.jogamp.newt.event.MouseAdapter;
import com.jogamp.newt.event.MouseEvent;
import com.jogamp.newt.opengl.GLWindow;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.util.Animator;

import android.os.Bundle;
import android.provider.Settings.System;
import android.util.Log;

import jogamp.newt.driver.android.NewtBaseActivity;
import jogamp.newt.driver.android.NewtVersionActivity;
import jogamp.newt.driver.android.NewtVersionActivityLauncher;

//j o g a m p . n e w t . d r i v e r . a n d r o i d . N e w t V e r s i o n A c t i v i t y L a u n c h e r
public class MainActivity extends NewtVersionActivity {//NewtBaseActivity {
    //@Override
    public void onCreate2(final Bundle state) {
        super.onCreate(state);

        final GLCapabilities caps =
                new GLCapabilities(GLProfile.get(GLProfile.GLES2));
        final GLWindow gl_window = GLWindow.create(caps);
        gl_window.setFullscreen(true);

        //this.setContentView(this.getWindow(), gl_window);

        gl_window.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(
                    final MouseEvent e) {
                if (e.getPressure(true) > 0.4f) { // show Keyboard
                    boolean kbVis = ((com.jogamp.newt.Window) e.getSource()).isKeyboardVisible();
                    ((com.jogamp.newt.Window) e.getSource()).setKeyboardVisible(!kbVis);
                }
            }
        });

        final Example example = new Example();

        // demo.enableAndroidTrace(true);
        gl_window.addGLEventListener(example);
        gl_window.getScreen().addMonitorModeListener(new MonitorModeListener() {
            @Override
            public void monitorModeChangeNotify(MonitorEvent monitorEvent) {

            }

            @Override
            public void monitorModeChanged(MonitorEvent monitorEvent, boolean b) {
                Log.e("System.err", "monitorModeChanged: " + monitorEvent);
            }


        });

        final Animator animator = new Animator(gl_window);
        //this.setAnimator(animator);

        gl_window.setVisible(true);
        animator.setUpdateFPSFrames(60, null);
        animator.resetFPSCounter();
        gl_window.resetFPSCounter();

    }
}
