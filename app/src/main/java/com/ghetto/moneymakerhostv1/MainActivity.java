package com.moneymaker.host;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity extends Activity {
    private final int background = Color.rgb(16, 21, 29);
    private final int panel = Color.rgb(27, 35, 47);
    private final int text = Color.rgb(239, 244, 250);
    private final int muted = Color.rgb(156, 169, 187);
    private final int green = Color.rgb(54, 211, 153);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(background);
        getWindow().setNavigationBarColor(background);

        ScrollView scroll = new ScrollView(this);
        scroll.setFillViewport(true);
        scroll.setBackgroundColor(background);
        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setPadding(dp(20), dp(24), dp(20), dp(24));
        scroll.addView(root);

        TextView brand = label("MoneyMaker", 28, text, true);
        root.addView(brand);
        TextView subtitle = label("HOST  •  VERSION 1", 12, green, true);
        root.addView(subtitle);
        addSpace(root, 22);

        LinearLayout status = card(root);
        status.addView(label("CONNECTION STATUS", 12, muted, true));
        status.addView(label("Demo mode — not connected to MT5", 17, text, true));
        addSpace(status, 6);
        status.addView(label("This starter app does not place trades or connect to a broker yet.", 13, muted, false));
        addSpace(root, 16);

        TextView marketTitle = label("MARKETS WATCHLIST", 15, text, true);
        root.addView(marketTitle);
        addSpace(root, 8);
        LinearLayout markets = card(root);
        addMarket(markets, "NAS100", "NASDAQ", "Waiting");
        addMarket(markets, "US30", "Dow Jones", "Waiting");
        addMarket(markets, "XAUUSD", "Gold", "Waiting");
        addMarket(markets, "EURUSD", "Euro / US Dollar", "Waiting");
        addMarket(markets, "GBPUSD", "Pound / US Dollar", "Waiting");
        addMarket(markets, "USDJPY", "US Dollar / Yen", "Waiting");
        addSpace(root, 16);

        TextView controlsTitle = label("CONTROLS", 15, text, true);
        root.addView(controlsTitle);
        addSpace(root, 8);
        Button connect = new Button(this);
        connect.setText("MT5 connection — coming next");
        connect.setAllCaps(false);
        connect.setTextColor(background);
        connect.setBackground(round(green, 14));
        connect.setOnClickListener(v -> showMessage(root, "This is a visual prototype. MT5 connection is not configured yet."));
        root.addView(connect, new LinearLayout.LayoutParams(-1, dp(52)));
        addSpace(root, 12);
        TextView note = label("Safety note: no live trading is enabled in this version. Connect a backend and test thoroughly before adding trade controls.", 12, muted, false);
        root.addView(note);

        setContentView(scroll);
    }

    private void addMarket(LinearLayout parent, String symbol, String description, String state) {
        LinearLayout row = new LinearLayout(this);
        row.setOrientation(LinearLayout.HORIZONTAL);
        row.setGravity(Gravity.CENTER_VERTICAL);
        row.setPadding(0, dp(10), 0, dp(10));
        TextView left = label(symbol + "\n" + description, 14, text, true);
        row.addView(left, new LinearLayout.LayoutParams(0, -2, 1));
        TextView right = label(state, 12, muted, false);
        right.setGravity(Gravity.CENTER_VERTICAL);
        row.addView(right);
        parent.addView(row);
        View line = new View(this);
        line.setBackgroundColor(Color.rgb(48, 59, 74));
        parent.addView(line, new LinearLayout.LayoutParams(-1, dp(1)));
    }

    private LinearLayout card(LinearLayout parent) {
        LinearLayout box = new LinearLayout(this);
        box.setOrientation(LinearLayout.VERTICAL);
        box.setPadding(dp(16), dp(16), dp(16), dp(16));
        box.setBackground(round(panel, 18));
        parent.addView(box, new LinearLayout.LayoutParams(-1, -2));
        return box;
    }

    private TextView label(String value, int size, int color, boolean bold) {
        TextView view = new TextView(this);
        view.setText(value);
        view.setTextSize(size);
        view.setTextColor(color);
        if (bold) view.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        return view;
    }

    private GradientDrawable round(int color, int radius) {
        GradientDrawable shape = new GradientDrawable();
        shape.setColor(color);
        shape.setCornerRadius(dp(radius));
        return shape;
    }

    private void addSpace(LinearLayout parent, int height) {
        View space = new View(this);
        parent.addView(space, new LinearLayout.LayoutParams(1, dp(height)));
    }

    private int dp(int value) {
        return (int) (value * getResources().getDisplayMetrics().density + 0.5f);
    }

    private void showMessage(LinearLayout root, String message) {
        android.widget.Toast.makeText(this, message, android.widget.Toast.LENGTH_LONG).show();
    }
}
