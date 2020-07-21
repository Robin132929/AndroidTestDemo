package com.robin.testdemo.activityjump;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.jumpraw.ad.GCAdNative;
import com.jumpraw.ad.GCAdSdk;
import com.jumpraw.ad.GCAdSlot;
import com.jumpraw.ad.GCFullScreenVideoAd;
import com.jumpraw.ad.GCRewardVideoAd;
import com.robin.testdemo.App;
import com.robin.testdemo.R;


public class VaActivity extends Activity {

    private static final String TAG = "JRTestVa";

    private GCAdNative gcAdNative;
    private GCFullScreenVideoAd gcFullScreenVideoAd;
    private GCRewardVideoAd gcRewardVideoAd;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this.getApplicationContext();

        findViewById(R.id.va_install_tv).setVisibility(View.GONE);

        findViewById(R.id.va_launch_tv).setVisibility(View.GONE);

        findViewById(R.id.va_gaid_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                killService(context);
            }
        });

        findViewById(R.id.va_mock_tv).setVisibility(View.GONE);

        findViewById(R.id.va_gms_tv).setVisibility(View.GONE);

        findViewById(R.id.pangle_load_fs).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    gcAdNative = GCAdSdk.getAdManager().createAdNative(VaActivity.this);

                    GCAdSlot gcAdSlot = new GCAdSlot.Builder()
                            .setSlotId(App.FullscreenSlotID)
                            .build();

                    gcAdNative.loadFullScreenVideoAd(gcAdSlot, new GCAdNative.FullScreenVideoAdListener() {
                        @Override
                        public void onFullScreenVideoAdLoad(GCFullScreenVideoAd ad) {
                            Log.i(TAG, "onFullScreenVideoAdLoad: ");
                            if (ad != null) {
                                gcFullScreenVideoAd = ad;
                            }
                        }

                        @Override
                        public void onError(int code) {
                            Log.i(TAG, "onError: " + code);
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        findViewById(R.id.pangle_show_fs).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (gcFullScreenVideoAd != null) {
                    gcFullScreenVideoAd.setFullScreenVideoAdInteractionListener(new GCFullScreenVideoAd.FullScreenVideoAdInteractionListener() {
                        @Override
                        public void onAdShow() {
                            Log.i(TAG, "onAdShow: ");
                        }

                        @Override
                        public void onAdVideoBarClick() {
                            Log.i(TAG, "onAdVideoBarClick: ");
                        }

                        @Override
                        public void onAdClose() {
                            Log.i(TAG, "onAdClose: ");
                        }

                        @Override
                        public void onVideoComplete() {
                            Log.i(TAG, "onVideoComplete: ");
                        }

                        @Override
                        public void onVideoError() {
                            Log.i(TAG, "onVideoError: ");
                        }

                        @Override
                        public void onSkippedVideo() {
                            Log.i(TAG, "onSkippedVideo: ");
                        }
                    });
                    gcFullScreenVideoAd.showFullScreenVideoAd(VaActivity.this);
                }
            }
        });

        findViewById(R.id.pangle_load_rv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    gcAdNative = GCAdSdk.getAdManager().createAdNative(VaActivity.this);

                    GCAdSlot rvSlot = new GCAdSlot.Builder()
                            .setSlotId(App.RewardVideoSlotID)
                            .build();

                    gcAdNative.loadRewardVideoAd(rvSlot, new GCAdNative.RewardVideoAdListener() {
                        @Override
                        public void onRewardVideoAdLoad(GCRewardVideoAd ad) {
                            Log.i(TAG, "onRewardVideoAdLoad: ");
                            gcRewardVideoAd = ad;
                        }

                        @Override
                        public void onError(int code) {
                            Log.i(TAG, "onError: ");
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        findViewById(R.id.pangle_show_rv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (gcRewardVideoAd != null) {
                    gcRewardVideoAd.setRewardAdInteractionListener(new GCRewardVideoAd.RewardVideoAdInteractionListener() {
                        @Override
                        public void onAdShow() {
                            Log.i(TAG, "onAdShow: ");
                        }

                        @Override
                        public void onAdVideoBarClick() {
                            Log.i(TAG, "onAdVideoBarClick: ");
                        }

                        @Override
                        public void onAdClose() {
                            Log.i(TAG, "onAdClose: ");
                        }

                        @Override
                        public void onVideoComplete() {
                            Log.i(TAG, "onVideoComplete: ");
                        }

                        @Override
                        public void onVideoError() {
                            Log.i(TAG, "onVideoError: ");
                        }

                        @Override
                        public void onRewardVerify() {
                            Log.i(TAG, "onRewardVerify: ");
                        }
                    });
                    gcRewardVideoAd.showRewardVideoAd(VaActivity.this);
                }
            }
        });
    }

    public static boolean isAlive(IBinder binder) {
        return binder != null && binder.isBinderAlive() && binder.pingBinder();
    }

    public static void killService(Context context) {
        ActivityManager mActivityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo appProcess : mActivityManager.getRunningAppProcesses()) {
            if (appProcess.processName.equals("com.jumpraw.testva:x")) {
                Log.i(TAG, "isMainProcess: >>>" + appProcess.processName);
                android.os.Process.killProcess(appProcess.pid);
            }
        }
    }

}
