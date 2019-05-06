package com.studio.karya.submission4.menu.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.studio.karya.submission4.R;
import com.studio.karya.submission4.menu.activity.DetailActivity;
import com.studio.karya.submission4.model.Content;
import com.studio.karya.submission4.utils.ParcelableUtil;

/**
 * Implementation of App Widget functionality.
 */
public class FavoriteWidget extends AppWidgetProvider {

    private static final String CLICK_ACTION = "CLICK_ACTION";
    public static final String EXTRA_ITEM = "EXTRA_ITEM";
    public static final String EXTRA_DATA = "EXTRA_DATA";
    public static final String TYPE_DATA = "TYPE_DATA";

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        if (intent.getAction() != null) {

            if (intent.getAction().equals(CLICK_ACTION)) {
                byte[] contentParcelable = intent.getByteArrayExtra(EXTRA_DATA);
                Parcel parcelable = ParcelableUtil.unmarshall(contentParcelable);
                Content content = new Content(parcelable);

                Intent moveDetail = new Intent(context, DetailActivity.class);
                moveDetail.putExtra(DetailActivity.TYPE, intent.getStringExtra(TYPE_DATA));
                moveDetail.putExtra(DetailActivity.DATA, content);
                moveDetail.putExtra(DetailActivity.EXTRA_POSITION, intent.getIntExtra(EXTRA_ITEM,0));
                context.startActivity(moveDetail);
            }
        }
    }

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        Intent intent = new Intent(context, StackWidgetService.class);
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
        intent.setData(Uri.parse(intent.toUri(Intent.URI_INTENT_SCHEME)));

        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.favorite_widget);
        views.setRemoteAdapter(R.id.stack_view, intent);
        views.setEmptyView(R.id.stack_view, R.id.empty_view);

        Intent moveIntent = new Intent(context, FavoriteWidget.class);
        moveIntent.setAction(CLICK_ACTION);
        moveIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);

        PendingIntent movePendingIntent = PendingIntent.getBroadcast(context, 0, moveIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        views.setPendingIntentTemplate(R.id.stack_view, movePendingIntent);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

