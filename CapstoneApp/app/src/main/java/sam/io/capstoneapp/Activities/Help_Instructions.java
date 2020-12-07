package sam.io.capstoneapp.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.animation.ArgbEvaluator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import sam.io.capstoneapp.Adapters.FAInstructAdapter;
import sam.io.capstoneapp.Adapters.HelpAdapter;
import sam.io.capstoneapp.Models.FirstAidModel;
import sam.io.capstoneapp.Models.HelpModel;
import sam.io.capstoneapp.R;

public class Help_Instructions extends AppCompatActivity {
    ViewPager viewPager;
    HelpAdapter adapter;
    List<HelpModel> models;
    ImageButton back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help__instructions);

        Intent intent = getIntent();
        Bundle extras  = intent.getExtras();
        String HelpName = extras.getString("HelpName");

        models = new ArrayList<>();
        back = findViewById(R.id.btnBack2);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        switch(HelpName){
            case "AppBar":
                models.add(new HelpModel(R.drawable.helpappbar1, "Bottom App Bar","This Application uses an Application Bar located at the bottom part. This Application Bar contains buttons that is used to navigate through the app."));
                models.add(new HelpModel(R.drawable.helphome2,"Home Button (House Icon)", "Navigate back to the Home Screen of The App"));
                models.add(new HelpModel(R.drawable.helpfirstaid3, "First Aid Button (Cross Icon) ","Navigate to access first aid list screen."));
                models.add(new HelpModel(R.drawable.helpcall4,"Call Directory Button (Phone Icon) ", "Navigate to Application’s call and details directory."));
                models.add(new HelpModel(R.drawable.helpmenu5, "Help and About (3 Vertical Dot Icon) ","Show 2 additional Help and About buttons."));
                models.add(new HelpModel(R.drawable.helpnav6,"Navigation Button (Location Pin Icon) ", "Navigate to the Location Searching screen."));
                models.add(new HelpModel(R.drawable.helphelp7, "Help (Overflow Button)","Navigate to Access/View App’s usage help "));
                models.add(new HelpModel(R.drawable.helpabout8,"About (Overflow Button)", "Navigate to About Screen containing information about the development team."));
                break;
            case "Using Call Directory List":
                models.add(new HelpModel(R.drawable.listver1, HelpName,"The application call directory uses a list that has its buttons hidden for vertical orientation to save space while providing necessary information and function."));
                models.add(new HelpModel(R.drawable.listver2, HelpName,"To show these buttons the list item/s must be swiped from right to left dragging from the right edge of the item/s."));
                models.add(new HelpModel(R.drawable.listver3, HelpName,"To hide revealed buttons, the item must be swiped from left to right dragging from the right edge of the item/s."));

                break;
            case "Using Map List":
                models.add(new HelpModel(R.drawable.listhor1, HelpName,"On the Location Searching Screen the application uses a horizontal list."));
                models.add(new HelpModel(R.drawable.listhor2, HelpName,"The items on this list works by simply tapping on it to get the point to point navigation to the desired location."));
                break;

            case "What Can You Search?":
                models.add(new HelpModel(R.drawable.search1, "Step 1 - " + HelpName,"The Call Directory and Location Searching screen that has most information both have a search functionality."));
                models.add(new HelpModel(R.drawable.search2, "Step 2 - " + HelpName,"The Search filter works by inputting desired contact name, categories, HMO, Services and offers."));
                break;
        }

        adapter = new HelpAdapter(models,this);
        viewPager = findViewById(R.id.viewPager2);
        viewPager.setAdapter(adapter);
        viewPager.setPadding(130,0,130,0);

    }
}