package com.lzh.petdiary_jetpack.utils;

import android.content.ComponentName;

import androidx.fragment.app.FragmentActivity;
import androidx.navigation.ActivityNavigator;
import androidx.navigation.NavController;
import androidx.navigation.NavGraph;
import androidx.navigation.NavGraphNavigator;
import androidx.navigation.Navigator;
import androidx.navigation.NavigatorProvider;
import androidx.navigation.fragment.FragmentNavigator;

import com.lzh.libnavannotation.ActivityDestination;
import com.lzh.petdiary_jetpack.model.Destination;
import com.lzh.petdiary_jetpack.navigator.FixFragmentNavigator;

import java.util.HashMap;
import java.util.Iterator;

public class NavGraphBuilder {
    public static void build(NavController controller, FragmentActivity activity, int containerId){
        NavigatorProvider provider = controller.getNavigatorProvider();
        NavGraph navGraph = new NavGraph(new NavGraphNavigator(provider));

//        FragmentNavigator fragmentNavigator = provider.getNavigator(FragmentNavigator.class);
        FixFragmentNavigator fragmentNavigator = new FixFragmentNavigator(activity, activity.getSupportFragmentManager(), containerId);
        provider.addNavigator(fragmentNavigator);

        ActivityNavigator activityNavigator = provider.getNavigator(ActivityNavigator.class);

        HashMap<String, Destination> destConfig = AppConfig.getDestConfig();
        Iterator<Destination> iterator = destConfig.values().iterator();
        while(iterator.hasNext()){
            Destination node = iterator.next();
            if(node.isFragment){
                FragmentNavigator.Destination destination = fragmentNavigator.createDestination();
                destination.setClassName(node.clazName);
                destination.setId(node.id);
                destination.addDeepLink(node.pageUrl);
                navGraph.addDestination(destination);
            }else{
                ActivityNavigator.Destination destination = activityNavigator.createDestination();
                destination.setId(node.id);
                destination.addDeepLink(node.pageUrl);
                destination.setComponentName(new ComponentName(AppGlobals.getApplication().getPackageName(),node.clazName));
                navGraph.addDestination(destination);
            }

            if(node.asStarter){
                navGraph.setStartDestination(node.id);
            }
        }
/*        for(Destination value: destConfig.values()){
        }*/

        controller.setGraph(navGraph);
    }
}
