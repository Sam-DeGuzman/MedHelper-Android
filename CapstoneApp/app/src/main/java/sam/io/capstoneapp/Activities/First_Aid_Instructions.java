package sam.io.capstoneapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

import sam.io.capstoneapp.Adapters.FAInstructAdapter;
import sam.io.capstoneapp.Models.FirstAidModel;
import sam.io.capstoneapp.R;

public class First_Aid_Instructions extends AppCompatActivity {
    ViewPager viewPager;
    FAInstructAdapter adapter;
    List<FirstAidModel> models;
    ImageButton back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first__aid__instructions);

        Intent intent = getIntent();
        Bundle extras  = intent.getExtras();
        String firstaidname = extras.getString("firstaidname");

        models = new ArrayList<>();
        back = findViewById(R.id.btnBack);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        switch(firstaidname){
            case "Heart Attack":
                models.add(new FirstAidModel(R.drawable.heartattack1, "Step 1:",firstaidname,getString(R.string.heartattack1)));
                models.add(new FirstAidModel(R.drawable.heartattack2,"Step 2:",firstaidname, getString(R.string.heartattack2)));
                models.add(new FirstAidModel(R.drawable.heartattack3, "Step 3:",firstaidname, getString(R.string.heartattack3)));
                models.add(new FirstAidModel(R.drawable.heartattack4, "Step 4:",firstaidname, getString(R.string.heartattack4)));
                models.add(new FirstAidModel(R.drawable.heartattack5, "Step 5:",firstaidname, getString(R.string.heartattack5)));
                models.add(new FirstAidModel(R.drawable.heartattack6, "Step 6:",firstaidname, getString(R.string.heartattack6)));
                models.add(new FirstAidModel(R.drawable.heartattack7, "Step 7:",firstaidname, getString(R.string.heartattack7)));
                models.add(new FirstAidModel(R.drawable.heartattack8, "Step 8:",firstaidname, getString(R.string.heartattack8)));
                models.add(new FirstAidModel(R.drawable.heartattackuc1, "Step 1 (Unconscious):",firstaidname, getString(R.string.heartattackuc1)));
                models.add(new FirstAidModel(R.drawable.heartattackuc2, "Step 2 (Unconscious):",firstaidname, getString(R.string.heartattackuc2)));
                models.add(new FirstAidModel(R.drawable.heartattackuc3, "Step 3 (Unconscious):",firstaidname, getString(R.string.heartattackuc3)));

                break;
            case "Stroke":
                models.add(new FirstAidModel(R.drawable.stroke1, "Step 1:",firstaidname,"Call 161 if you see any single one of these signs of a stroke."));
                models.add(new FirstAidModel(R.drawable.stroke2, "Step 2:",firstaidname,"Even if the symptoms disappear while you're waiting for the ambulance, It is still important to go to the hospital for an assessment."));
                models.add(new FirstAidModel(R.drawable.stroke3,  "Step 3:",firstaidname,"While you are waiting for the ambulance to arrive lay the casualty down."));
                models.add(new FirstAidModel(R.drawable.stroke4, "Step 4:",firstaidname,"Do not offer them any food or drink, as they may not be able to swallow effectively."));
                break;
            case "Asthma":
                models.add(new FirstAidModel(R.drawable.asthmawi1, "Step 1 (With Inhaler):",firstaidname,"Be calm and reassuring, do not leave them alone."));
                models.add(new FirstAidModel(R.drawable.asthmawi2, "Step 2 (With Inhaler):",firstaidname, "Shake the blue reliever puffer, use a spacer if you have one, Give 4 separate puffs into the spacer and Take 4 breaths from the spacer after each puff."));
                models.add(new FirstAidModel(R.drawable.asthmawi3, "Step 3 (With Inhaler):",firstaidname, "If there is no improvement, repeat step 2."));
                models.add(new FirstAidModel(R.drawable.asthmawi4, "Step 4 (With Inhaler):",firstaidname, "Tell the operator the person is having an asthma attack and Keep giving 4 puffs every 4 minutes while you wait for emergency assistance."));
                models.add(new FirstAidModel(R.drawable.asthmawo1, "Step 1 (Without Inhaler):",firstaidname, "Never, ever let up on your asthma medications even if you feel your condition has improved."));
                models.add(new FirstAidModel(R.drawable.asthmawo2, "Step 2 (Without Inhaler):",firstaidname, "Asthma needs constant care and proper management at all times. Your very life depends on it. "));
                models.add(new FirstAidModel(R.drawable.asthmaother1, "Step 1 (Other Cases):",firstaidname,"Sit upright - Stop whatever you are doing and sit upright. Bending over or lying down can constrict your breathing even more"));
                models.add(new FirstAidModel(R.drawable.asthmaother2, "Step 2 (Other Cases):",firstaidname, "Take long, deep breaths - This helps to slow down your breathing and prevent hyperventilation. Breathe in through your nose and breathe out through your mouth."));
                models.add(new FirstAidModel(R.drawable.asthmaother3, "Step 3 (Other Cases):",firstaidname, "Stay calm   Staying calm may prevent further tightening of your chest muscles and make your breathing easier. "));
                models.add(new FirstAidModel(R.drawable.asthmaother4, "Step 4 (Other Cases):",firstaidname, "Get away from the trigger- The asthma attack could be triggered by dust, cigarette smoke, or the smell of chemicals (e.g., ammonia, chlorine gas, sulfur dioxide). Get away from the trigger as soon as possible and go to an air-conditioned environment or any place with clean air. "));
                models.add(new FirstAidModel(R.drawable.asthmaother5, "Step 5 (Other Cases):",firstaidname, "Take a hot caffeinated drink - it helps clear airways slightly, providing relief for an hour or two. it contains a chemical similar to the old asthma medicine theophylline. Many doctors deduce, but the amounts in these drinks are small to do much good."));
                models.add(new FirstAidModel(R.drawable.asthmaother6, "Step 6 (Other Cases):",firstaidname, "Seek emergency medical help- If the wheezing, coughing, and breathing difficulty do not subside after a period of rest, seek immediate medical attention. "));

                break;
            case "Pneumonia":
                models.add(new FirstAidModel(R.drawable.pneumonia1, "Step 1:", firstaidname, "Control your fever with aspirin, nonsteroidal anti-inflammatory drugs (NSAIDs, such as ibuprofen or naproxen), or acetaminophen. DO NOT give aspirin to children."));
                models.add(new FirstAidModel(R.drawable.pneumonia2, "Step 2:", firstaidname, "Drink plenty of fluids to help loosen secretions and bring up phlegm."));
                models.add(new FirstAidModel(R.drawable.pneumonia3, "Step 3:", firstaidname, "Do not take cough medicines without first talking to your doctor. Coughing is one way your body works to get rid of an infection. If your cough is preventing you from getting the rest you need, ask your doctor about steps you can take to get relief."));
                models.add(new FirstAidModel(R.drawable.pneumonia4, "Step 4:", firstaidname, "Drink warm beverages, take steamy baths and use a humidifier to help open your airways and ease your breathing. Contact your doctor right away if your breathing gets worse instead of better over time."));
                models.add(new FirstAidModel(R.drawable.pneumonia5, "Step 5:", firstaidname, "Stay away from smoke to let your lungs heal. This includes smoking, secondhand smoke and wood smoke. Talk to your doctor if you are a smoker and are having trouble staying smoke free while you recover. This would be a good time to think about quitting for good."));

                break;
            case "Diabetes":
                models.add(new FirstAidModel(R.drawable.diabetes1hl, "Step 1 (Blood Sugar is High or Low):",firstaidname,"Give them something sugary anyway, as this will quickly relieve low blood sugar and is not likely to cause high blood sugar."));
                models.add(new FirstAidModel(R.drawable.diabetes2hl, "Step 2 (Blood Sugar is High or Low):",firstaidname,"If they don't make rapid improvement, then call 161 for medical assistance."));
                models.add(new FirstAidModel(R.drawable.diabetes3hl, "Step 3 (Blood Sugar is High or Low):",firstaidname,"Stay with them until they feel completely better."));
                models.add(new FirstAidModel(R.drawable.diabetes4hl, "Step 4 (Blood Sugar is High or Low):",firstaidname,"If they have their glucose testing kit with them, ask if they would like help to use it to check their glucose level."));
                models.add(new FirstAidModel(R.drawable.diabetes2hl, "Step 1 (Diabetic Coma):",firstaidname, "Call 161 straight away for medical help and say that you suspect hyperglycemia."));
                models.add(new FirstAidModel(R.drawable.diabetescoma2, "Step 2 (Diabetic Coma):",firstaidname, "While you wait for help to arrive, keep checking their breathing, pulse, and level of response."));
                models.add(new FirstAidModel(R.drawable.diabetescoma3, "Step 3 (Diabetic Coma):",firstaidname, "If they lose responsiveness at any point, open their airway, check their breathing and prepare to treat someone who's become unconscious."));
                models.add(new FirstAidModel(R.drawable.diabetes4hl, "Step 1 (Low Blood Sugar):",firstaidname, "If they have their glucose gel, ask if they need help taking it. "));
                models.add(new FirstAidModel(R.drawable.diabeteslowblood2, "Step 2 (Low Blood Sugar):",firstaidname, "If they do not, you will need to give them something sugary like a fruit juice, a fizzy drink, 3 teaspoons of sugar, or some sugary sweets. "));
                models.add(new FirstAidModel(R.drawable.diabetes1hl, "Step 3 (Low Blood Sugar):",firstaidname,"If they improve swiftly, then give them more sugary food or drink and let them rest."));
                models.add(new FirstAidModel(R.drawable.diabetes3hl, "Step 4 (Low Blood Sugar):",firstaidname, "While waiting, keep checking their responsiveness, breathing, and pulse. "));
                models.add(new FirstAidModel(R.drawable.diabetes2hl, "Step 5 (Low Blood Sugar):",firstaidname, "If they do not improve quickly, look for any other causes and then call 161 for medical assistance."));

                break;
            case "High Blood Pressure":
                models.add(new FirstAidModel(R.drawable.highblood1,"Step 1:",firstaidname,"Rest at least 15 minutes and stop whatever you are doing so try to breathe deeply. It is best to sit on one side to avoid falling."));
                models.add(new FirstAidModel(R.drawable.highblood2,"Step 2:",firstaidname, "Get a blood pressure on your acquaintance or nurse after you rest for a few minutes."));
                models.add(new FirstAidModel(R.drawable.highblood3,"Step 3:",firstaidname, "Take your maintenance medication with a doctor's prescription"));
                models.add(new FirstAidModel(R.drawable.highblood4,"Step 4:",firstaidname, "Drink warm water to help relax your blood vessels."));
                models.add(new FirstAidModel(R.drawable.highblood5,"Step 5:",firstaidname,"Drinking pineapple juice helps it lower sodium levels in the body and effectively reduce pressure."));
                models.add(new FirstAidModel(R.drawable.highblood6,"Step 6:",firstaidname,"Eat foods high in potassium such as banana, orange, or tomato juice that help balance blood pressure"));
                models.add(new FirstAidModel(R.drawable.highblood7,"Step 7:",firstaidname,"Try to relax and don't panic and get rushed to the nearest hospital."));
                break;
            case "Burns":
                models.add(new FirstAidModel(R.drawable.burnsthermal1, "Step 1 (3rd Degree Thermal Burns):",firstaidname, "Cover the burned area with a dry and non-sticking dressing. Do not apply anything unto the skin. Immersing into water is not advisable."));
                models.add(new FirstAidModel(R.drawable.burnsthermal2, "Step 2 (3rd Degree Thermal Burns):",firstaidname, "PREPARE FOR EMERGENCY TRANSFER, monitor for signs of dehydration and shock. Keep the victim warm by covering with blankets during transfer.Extend the flexed burned extremities to avoid contractures."));
                models.add(new FirstAidModel(R.drawable.chemicalburns1, "Step 1 (Chemical Burns):",firstaidname, "Immediately remove the chemical by flushing with water. Remove the victim’s contaminated clothing. Use mild soap for the final rinse."));
                models.add(new FirstAidModel(R.drawable.chemicalburns2, "Step 2 (Chemical Burns):",firstaidname, "Pat dry the area using a clean cloth and apply dressing into the affected area."));
                models.add(new FirstAidModel(R.drawable.chemicalburns3, "Step 3 (Chemical Burns):",firstaidname, "If the chemical is in the eye, flush for at least 20 minutes using low pressure."));
                models.add(new FirstAidModel(R.drawable.chemicalburns4, "Step 4 (Chemical Burns):",firstaidname, "Seek medical attention immediately for chemical burns."));
                break;
            case "Eye Injury":
                models.add(new FirstAidModel(R.drawable.eyespecks1, "Step 1 (Specks in the Eye):",firstaidname, "Do not rub the eye."));
                models.add(new FirstAidModel(R.drawable.eyespecks2, "Step 2 (Specks in the Eye):",firstaidname, "Do not use tweezers or other sharp implements to remove."));
                models.add(new FirstAidModel(R.drawable.eyespecks3, "Step 3 (Specks in the Eye):",firstaidname, "Flush the eye with large amounts of water."));
                models.add(new FirstAidModel(R.drawable.eyespecks4, "Step 4 (Specks in the Eye):",firstaidname, "See a doctor if the speck does not wash out."));
                models.add(new FirstAidModel(R.drawable.eyecuts1, "Step 1 (Cuts, Punctures, Foreign Objects in the Eye):",firstaidname, "Do not wash out the eye."));
                models.add(new FirstAidModel(R.drawable.eyecuts2, "Step 2 (Cuts, Punctures, Foreign Objects in the Eye):",firstaidname, "Do not try to remove a foreign object stuck in the eye."));
                models.add(new FirstAidModel(R.drawable.eyecuts3, "Step 3 (Cuts, Punctures, Foreign Objects in the Eye):",firstaidname, "Seek immediate medical attention."));
                models.add(new FirstAidModel(R.drawable.eyecuts4, "Step 4 (Cuts, Punctures, Foreign Objects in the Eye):",firstaidname, "Try to avoid rubbing the eye."));
                models.add(new FirstAidModel(R.drawable.eyechemicals1, "Step 1 (Chemical Burns):",firstaidname, "Immediately flush the eye with cool water."));
                models.add(new FirstAidModel(R.drawable.eyechemicals2, "Step 2 (Chemical Burns):",firstaidname, "Open the eyelids as wide as possible."));
                models.add(new FirstAidModel(R.drawable.eyechemicals3, "Step 3 (Chemical Burns):",firstaidname, "Continue flushing for at least 15 minutes."));
                models.add(new FirstAidModel(R.drawable.eyechemicals4, "Step 4 (Chemical Burns):",firstaidname, "Seek immediate medical attention."));
                break;
            case "Electric Shock":
                models.add(new FirstAidModel(R.drawable.electricshock1, "Step 1:" ,firstaidname, "Please don’t touch the victim until electrical contact is broken."));
                models.add(new FirstAidModel(R.drawable.electricshock2, "Step 2:" ,firstaidname, "Unplug or switch off the source of electricity."));
                models.add(new FirstAidModel(R.drawable.electricshock3, "Step 3:" ,firstaidname, "If he/she is not breathing, then seek medical attention immediately."));
                break;
            case "Shock":
                models.add(new FirstAidModel(R.drawable.stroke4, "Step 1:" ,firstaidname, "Do not give anything to eat or drink."));
                models.add(new FirstAidModel(R.drawable.shock2, "Step 2:" ,firstaidname, "Keep the/she warm (not hot) by the use of blankets or clothes."));
                models.add(new FirstAidModel(R.drawable.shock3, "Step 3:" ,firstaidname, "Lay the victim on his/her back, but do not move him/her if there's a back or neck injury."));
                models.add(new FirstAidModel(R.drawable.shock4, "Step 4:" ,firstaidname, "Raise the patient's feet and legs with a pillow."));
                break;
            case "Dog Bite":
                models.add(new FirstAidModel(R.drawable.dogbite1, "Step 1:" ,firstaidname, "Wipe the saliva away from the wound using a clean cloth or handkerchief."));
                models.add(new FirstAidModel(R.drawable.dogbite2, "Step 2:" ,firstaidname, "Do not come in contact with the saliva that gets wiped away."));
                models.add(new FirstAidModel(R.drawable.dogbite3, "Step 3:" ,firstaidname, "Wash the wound thoroughly with plenty of soap and water."));
                models.add(new FirstAidModel(R.drawable.dogbite4, "Step 4:" ,firstaidname, "Cover the wound with a dry, sterile dressing."));
                models.add(new FirstAidModel(R.drawable.dogbite5, "Step 5:" ,firstaidname, "Get medical aid or send the patient to the hospital as soon as possible."));
                break;
        }

        adapter = new FAInstructAdapter(models,this);
        viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(adapter);
        viewPager.setPadding(130,0,130,0);

    }
}
