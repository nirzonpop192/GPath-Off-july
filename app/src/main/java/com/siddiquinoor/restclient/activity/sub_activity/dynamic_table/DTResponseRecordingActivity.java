package com.siddiquinoor.restclient.activity.sub_activity.dynamic_table;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.util.Base64;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;


import com.siddiquinoor.restclient.R;
import com.siddiquinoor.restclient.data_model.DTQResModeDataModel;
import com.siddiquinoor.restclient.data_model.DTResponseTableDataModel;
import com.siddiquinoor.restclient.data_model.DT_ATableDataModel;
import com.siddiquinoor.restclient.data_model.FreezeDataModel;
import com.siddiquinoor.restclient.data_model.adapters.AssignDataModel;
import com.siddiquinoor.restclient.fragments.BaseActivity;
import com.siddiquinoor.restclient.manager.SQLiteHandler;
import com.siddiquinoor.restclient.manager.sqlsyntax.SQLServerSyntaxGenerator;
import com.siddiquinoor.restclient.parse.Parse;
import com.siddiquinoor.restclient.utils.CameraUtils;
import com.siddiquinoor.restclient.utils.KEY;
import com.siddiquinoor.restclient.data_model.adapters.DTQTableDataModel;
import com.siddiquinoor.restclient.views.helper.SpinnerHelper;
import com.siddiquinoor.restclient.views.notifications.ADNotificationManager;
import com.siddiquinoor.restclient.views.notifications.CustomToast;
import com.siddiquinoor.restclient.views.spinner.SpinnerLoader;

import java.io.ByteArrayOutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import static com.siddiquinoor.restclient.utils.CameraUtils.CAMERA_REQUEST_1;


public class DTResponseRecordingActivity extends BaseActivity implements CompoundButton.OnCheckedChangeListener {

    public static final String TEXT = "Text";
    public static final String NUMBER = "Number";
    public static final String Date = "Date";
    public static final String Date_OR_Time = "Datetime";
    public static final String TEXT_BOX = "Textbox";
    //public static final String PHOTO = "Photo";
    public static final String COMBO_BOX = "Combobox";
    public static final String GEO_LAYER_3 = "Geo Layer 3";

    public static final String GEO_LAYER_2 = "Geo Layer 2";
    public static final String GEO_LAYER_1 = "Geo Layer 1";
    public static final String GEO_LAYER_4 = "Geo Layer 4";
    public static final String GEO_LAYER_ADDRESS = "Geo Layer Address";
    public static final String SERVICE_SITE = "Service Site";
    public static final String DISTRIBUTION_POINT = "Distribution Point";
    public static final String COMMUNITY_GROUP = "Community Group";
    public static final String COMMUNITY_GROUP_PG = "Community Group (PG)";
    public static final String COMMUNITY_GROUP_IG = "Community Group (IG)";
    public static final String COMMUNITY_GROUP_WE = "Community Group (WE)";
    public static final String COMMUNITY_GROUP_MG = "Community Group (MG)";
    public static final String COMMUNITY_GROUP_LG = "Community Group (LG)";

    public static final String ORGANIZATION_LIST = "Organization List";
    public static final String CHECK_BOX = "Checkbox";
    public static final String RADIO_BUTTON = "Radio Button";
    public static final String DATE_TIME = "Datetime";
    public static final String GPS_LONG = "GPS LONG";
    public static final String GPS_LATD = "GPS LATD";
    public static final String GPS_COORDINATE = "GPS Coordinate";
    public static final String PHOTO = "Image";
    public static final String DATE = "Date";
    public static final String RADIO_BUTTON_N_TEXTBOX = "Radio Button, Textbox";
    public static final String CHECKBOX_N_TEXTBOX = "Checkbox, Textbox";
    public static final String LOOKUP_LIST = "Lookup List";

    public static final String COMMNITY_ANIMAL = "Commnity Animal";

//    private CustomToast cusToast;

    /**
     * Tag response class
     */
    private static final String TAG = DTResponseRecordingActivity.class.getSimpleName();
    /**
     * Database helper
     */
    private SQLiteHandler sqlH;

    /**
     * alert Dialog
     */
    private ADNotificationManager dialogManager;

    /**
     * mContext Class System Context variable
     */
    private final Context mContext = DTResponseRecordingActivity.this;
    private AssignDataModel.DynamicDataIndexDataModel dyIndex;
    private int totalQuestion;

    /**
     * text view where  question v appear
     */
    private TextView tv_DtQuestion;
    private Button btnNextQues;
    private Button btnHome;
    private DTQTableDataModel mDTQTable;

    /**
     * this button load the previous Question
     */
    private Button btnPreviousQus;

    /**
     * used in {@link #onActivityResult(int, int, Intent)}
     */
    Bitmap mPhotoBitmap;

    /**
     * fileUri is use for image rotation
     */
    //private Uri fileUri;
    /**
     * question index
     */
    int mQusIndex;
    /**
     * For Date time picker
     */
    private SimpleDateFormat mFormat = new SimpleDateFormat("MM-dd-yyyy", Locale.ENGLISH);
    private Calendar calendar = Calendar.getInstance();
    /**
     * Dynamic view
     */
    private Spinner dt_spinner;
    private ImageView dt_photo;

    /**
     * dynamic edit text
     */
    private EditText dt_edt;

    /**
     * date picker
     */
    private TextView _dt_tv_DatePickerNLatLong;
    private RadioGroup mRadioGroup;
//    private RadioButton rdbtn;
    /**
     * To determined the either any Check box is Selected or nor
     */
    private int countChecked = 0;

    /**
     * spinner's item code
     */
    private String idSpinner;

    /**
     * spinner's item  values
     */
    private String strSpinner;


    /**
     * DTQResMode
     */
    DTQResModeDataModel mDTQResMode;

    /**
     * #mDTATable is Deliminator of Check Box item &  value
     * it is assigned by {@link #displayQuestion(DTQTableDataModel)} method
     */
    List<DT_ATableDataModel> mDTATables;
    /**
     * List for Dynamic
     */

    private List<RadioButton> mRadioButton_List = new ArrayList<RadioButton>();

    private List<CheckBox> mCheckBox_List = new ArrayList<CheckBox>();

    /**
     * we use global for
     */
    private RadioGroup mRadioGroupForRadioAndEditText;

    /**
     * this is a list of Radio button which is created in runtime dynamically.
     * in a linear layout  {@link #ll_editText}
     */
    private List<RadioButton> mRadioButtonForRadioAndEdit_List = new ArrayList<RadioButton>();
    private List<EditText> mEditTextForRadioAndEdit_List = new ArrayList<EditText>();
    private List<EditText> mEditTextForCheckBoxAndEdit_List = new ArrayList<EditText>();
    private List<CheckBox> mCheckBoxForCheckBoxAndEdit_List = new ArrayList<CheckBox>();

    private LinearLayout dt_layout_Radio_N_EditText;

    /**
     * this is base64 image to upload
     */
    private String imageString;

    /**
     * Freeze flag
     * 2. all variables that remains on or before the selected question "serial" will be stored with the "same" value that has been saved until Freeze = True.
     * <p>
     * 3. If user want to continue (Yes) at the end of the responses then if Freeze = true then the interface will take the user to the next question serial after the Freeze point.
     * <p>
     * 4. If user do not want to continue (No) at the end of the responses then set Freeze =false and then interface will take the user to the first question serial.
     * <p>
     * 5. point 4 will be effective if user want to quite in the middle of saving responses then set Freeze =false.
     * <p>
     * 6. If there are skip rules associated with the selected question then it will return a message saying "Freeze will not be possible at this point."
     */
    private boolean isFreeze = false;

    /**
     * This is temporary list, which will store the saved in the list til {@link #isFreeze = true}
     */
    private List<FreezeDataModel> mBufferingList = new ArrayList<FreezeDataModel>();
    private int mFreezeTerminatedIndex = -1;
    private boolean isSkipRullAssociated = false;

    public String getImageString() {
        return imageString;
    }

    /**
     * dt_SkipDTQues
     */
    DTQTableDataModel dt_SkipDTQues = null;

    /**
     * @param imageString base64 string of image
     */
    public void setImageString(String imageString) {
        this.imageString = imageString;
    }

    /**
     * Layout
     */
    private LinearLayout parent_layout_onlyFor_CB;
    private LinearLayout parent_layout_FOR_CB_N_ET;
    /**
     * This layout is child of
     * {@link #parent_layout_FOR_CB_N_ET}
     */
    private LinearLayout subParent_CB_layout_FOR_CB_N_ET;
    /**
     * This layout is child of
     * {@link #parent_layout_FOR_CB_N_ET}
     */
    private LinearLayout subParent_ET_layout_FOR_CB_N_ET;

    private LinearLayout ll_editText;


    /**
     * mDTResponse Sequence  DTRSeq
     */
    private int mDTRSeq;
    private int surveyNumber;

    /**
     * used in  {@link #showPhotoCaptureDialog(int)}  method
     */
    private Dialog imageCaptureOptionDialog;

    private ToggleButton tBtnFreezNUnfeez;

    private boolean isSkipQuestion = false;
    private DT_ATableDataModel mSkipDTATable = null;


    private Location mLocation;
    /**
     * set up location listener
     */
    private LocationListener mLocationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            mLocation = location;
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
        }

        @Override
        public void onProviderEnabled(String provider) {
        }

        @Override
        public void onProviderDisabled(String provider) {
        }
    };

    /**
     * Refer the all the necessary view in java object
     */
    private void viewReference() {
        tv_DtQuestion = (TextView) findViewById(R.id.tv_DtQuestion);
        btnHome = (Button) findViewById(R.id.btnHomeFooter);
        btnNextQues = (Button) findViewById(R.id.btn_dt_next);
        btnPreviousQus = (Button) findViewById(R.id.btn_dt_preview);
        Button btnGone = (Button) findViewById(R.id.btnRegisterFooter);
        btnGone.setVisibility(View.GONE);

        // next & preview button
        parent_layout_onlyFor_CB = (LinearLayout) findViewById(R.id.ll_checkBox);
        _dt_tv_DatePickerNLatLong = (TextView) findViewById(R.id.tv_dtTimePicker);
        dt_edt = (EditText) findViewById(R.id.edt_dt);
        dt_spinner = (Spinner) findViewById(R.id.dt_sp);
        dt_photo = (ImageView) findViewById(R.id.dt_iv_photo);
        mRadioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        ll_editText = (LinearLayout) findViewById(R.id.llEditText);
        mRadioGroupForRadioAndEditText = (RadioGroup) findViewById(R.id.radioGroupForRadioAndEdit);

        //CheckBox and EditText
        parent_layout_FOR_CB_N_ET = (LinearLayout) findViewById(R.id.ll_CheckBoxAndEditTextParent);
        subParent_CB_layout_FOR_CB_N_ET = (LinearLayout) findViewById(R.id.ll_checkBoxAndEditTextCheckbox);
        subParent_ET_layout_FOR_CB_N_ET = (LinearLayout) findViewById(R.id.et_CheckBoxAndEditText);
        dt_layout_Radio_N_EditText = (LinearLayout) findViewById(R.id.ll_radioGroupAndEditText);

        tBtnFreezNUnfeez = (ToggleButton) findViewById(R.id.toggBtn_freezNUnfeez);
    }


    /**
     * initial state
     * also views refer
     * initiate {@link #sqlH} & {@link #dialogManager}
     * invoke :{@link #viewReference()} method  for instance
     */

    private void inti() {
        viewReference();
        sqlH = new SQLiteHandler(mContext);
//        cusToast = new CustomToast(mContext);

        // initiate with dialog Manager
        dialogManager = new ADNotificationManager();

        //get intent
        Intent intent = getIntent();
        dyIndex = intent.getParcelableExtra(KEY.DYNAMIC_INDEX_DATA_OBJECT_KEY);
        totalQuestion = intent.getIntExtra(KEY.DYNAMIC_T_QUES_SIZE, 0);
        initialWithFirstQues();
    }


    /**
     * this method load previous Question. from data set
     */

    private void getPreviousQuestion() {

        --mQusIndex;
        hideViews();                            // hide all previous view

        if (mQusIndex >= 1) {                   // to check does index exceed the minimum  value

            DTQTableDataModel nextQus = loadPreviousQuestion(dyIndex.getDtBasicCode(), mQusIndex);
            displayQuestion(nextQus);

            if (mQusIndex == 1) {
                btnPreviousQus.setVisibility(View.INVISIBLE);
            }
        } else
            mQusIndex = 1;                         // set index at initial stage


    }

    /**
     * Load the next Question
     */

    private void getNextQuestion() {

        /** for 1st question the {@link #btnPreviousQus }previous button will be invisible
         * if user go for the second question  this block  will check , is previous button
         * invisible if it is true set the button visible
         */
        if (btnPreviousQus.getVisibility() == View.INVISIBLE)
            btnPreviousQus.setVisibility(View.VISIBLE);


        ++mQusIndex;                                                        // increments the question no index

        if (mQusIndex <= totalQuestion)                                     //to check does index exceed the maximum value
            hideViews();                                                    // hide all views


        if (mQusIndex < totalQuestion) {

            DTQTableDataModel nextQus = skipToQuestionCheck(mQusIndex);         //  add skip rules

            if (nextQus != null)
                displayQuestion(nextQus);

            // set arrow icon instead of stop icon . set previous  state
            removeStopIconNextButton(btnPreviousQus);

            // no need to delete bellow code
            //   if (mQusIndex == totalQuestion - 1) {
            //   addSaveIconButton(btnNextQues);
            //   }


        } else if (mQusIndex == totalQuestion) {

            DTQTableDataModel nextQus = skipToQuestionCheck(mQusIndex); //  add skip rules

            if (nextQus != null) {
                if (nextQus.getqText() != null && !nextQus.getqText().equals("Thank You!") && !nextQus.getqText().equals("Thank you!"))
                    displayQuestion(nextQus);
                else {
                    getNextQuestion();
                }
            }


            // don't delete below code version 12's bug generated because of this piece of sheet
            // one set complete
            //      compilationFunctionNMessage();

        } else if (mQusIndex > totalQuestion) {             // if all  questions set is complete

            // one set complete
            compilationFunctionNMessage();
            continueDialog();
        }
    }

    private void setListener() {
        btnNextQues.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveProcessValidation();
            }
        });
        btnPreviousQus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mQusIndex != totalQuestion) {
                    removeStopIconNextButton(btnNextQues);
                }
                getPreviousQuestion();
            }
        });
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToHomeWithDialog();
            }
        });
        tBtnFreezNUnfeez.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                /**
                 *  If there are skip rules associated with the selected question then it will
                 *  return a message saying "Freeze will not be possible at this point."
                 */
                if (!isSkipRullAssociated) {   // here skip ques is false

                    if (isChecked) {
                        freezeNUnfreezeDialog(true, "Sure to Freeze ?");
                    } else {
                        freezeNUnfreezeDialog(false, "Sure to UnFreeze ?");
                    }


                } else {

                    CustomToast.show(mContext, "Freeze will not be possible at this point.");
                    tBtnFreezNUnfeez.setChecked(false);
                }

            }
        });
    }

    /**
     * @param sIState savedInstanceState
     */

    @Override
    protected void onCreate(Bundle sIState) {
        super.onCreate(sIState);
        setContentView(R.layout.activity_dt__qustion);
        inti();
        setListener();
    }


    private void freezeNUnfreezeDialog(final boolean freeze, String msg) {

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(mContext);
        /**
         *  in unfinished condition if anyone press home button
         *  Setting Dialog Title
         */

        alertDialog.setTitle("Freeze");


        // String massage;
        if (freeze) {


            // On pressing Settings button
            alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                    isFreeze = true;
                    mFreezeTerminatedIndex = mQusIndex;

                    //  set custom toast
                    String custMsg = " Freeze point";
                    custMsg = custMsg + (freeze ? " Activated " : " Deactivated ");
                    CustomToast.show(mContext, custMsg);

                }
            });

            alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                    tBtnFreezNUnfeez.setChecked(!freeze);

                }
            });

        } else {

            // On pressing Settings button


            alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();

                    isFreeze = false;
                    mFreezeTerminatedIndex = -1;

                    mBufferingList.clear();
                    // check incomplete response

                    if (mQusIndex < totalQuestion) {

                        /**
                         * @see DTResponseRecordingActivity#deleteFromResponseTable(String, String, String, String, String, String, String, String, int, SQLiteHandler)
                         */
                        deleteFromResponseTable(dyIndex.getDtBasicCode(), dyIndex.getcCode(), dyIndex.getDonorCode(), dyIndex.getAwardCode(), dyIndex.getProgramCode(), dyIndex.getOpMode(), dyIndex.getOpMonthCode(), getStaffID(), mDTRSeq, sqlH);

                        initialWithFirstQues();

                    }
//  set custom toast
                    String custMsg = " Freeze point";
                    custMsg = custMsg + (freeze ? " Activated " : " Deactivated ");
                    CustomToast.show(mContext, custMsg);

                }
            });

            alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                    tBtnFreezNUnfeez.setChecked(freeze);
                }
            });

        }
        alertDialog.setMessage(msg);
        alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        alertDialog.show();
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void addIconHomeButton() {
        btnHome.setText("");
        Drawable imageHome = getResources().getDrawable(R.drawable.home_b);
        btnHome.setCompoundDrawablesRelativeWithIntrinsicBounds(imageHome, null, null, null);
        setPaddingButton(mContext, imageHome, btnHome);
    }


    /**
     * this method set icon in method
     *
     * @param button button view
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void addNextOrPreviousButton(Button button) {
        button.setText("");
        Drawable imageHome;
        if (button == btnNextQues)
            imageHome = getResources().getDrawable(R.drawable.goto_forward);
        else
            imageHome = getResources().getDrawable(R.drawable.goto_back);

        button.setCompoundDrawablesRelativeWithIntrinsicBounds(imageHome, null, null, null);
        setPaddingButton(mContext, imageHome, button);
    }

    /**
     * calling getWidth() and getHeight() too early:
     * When  the UI has not been sized and laid out on the screen yet..
     *
     * @param hasFocus the value will be true when UI is focus
     */

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        addIconHomeButton();
        addNextOrPreviousButton(btnNextQues);
        addNextOrPreviousButton(btnPreviousQus);
    }

    /**
     *
     */
    private void goToHomeWithDialog() {

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(mContext);
        /**
         *  in unfinished condition if anyone press home button
         *  Setting Dialog Title
         */

        alertDialog.setTitle("Home");

        String massage;
        if (mQusIndex < totalQuestion) {

            massage = "Your response is incomplete.\nDo you want to quit ?";
            // On pressing Settings button
            alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                    dialogManager.deleteResponseConfirmationDialog(DTResponseRecordingActivity.this, dyIndex.getDtBasicCode(), dyIndex.getcCode(), dyIndex.getDonorCode(), dyIndex.getAwardCode(), dyIndex.getProgramCode(), dyIndex.getOpMode(), dyIndex.getOpMonthCode(), getStaffID(), mDTRSeq, sqlH);
                }
            });

        } else {
            massage = " Do you want to go to Home page ?";
            // On pressing Settings button
            alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    goToMainActivity((Activity) mContext);

                }
            });

        }
        alertDialog.setMessage(massage);
        alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        alertDialog.show();
    }


    /**
     * Change The Color of Question  to Indicate the Error occurred
     * if user
     */
    private void errorIndicator() {
        tv_DtQuestion.setTextColor(getResources().getColor(R.color.red));
    }

    /**
     * Change the color of question at normal stage
     */
    private void normalIndicator() {
        tv_DtQuestion.setTextColor(getResources().getColor(R.color.blue_dark));
    }


    /**
     * loadDT_QResMode(String) is equivalent to ans view loader
     *
     * @param resMode repose Mode
     */
    private void loadDT_QResMode(String resMode) {

        /**
         *  the {@link #mDTQResMode} is needed in the save process in {@link #saveProcessValidation()}
         */
        mDTQResMode = sqlH.getDT_QResMode(resMode);
        String responseControl = mDTQResMode.getDtResponseValueControl();
        String dataType = mDTQResMode.getDtDataType();
        String resLupText = mDTQResMode.getDtQResLupText();


        //Resort Data if Data exists
        DTResponseTableDataModel dtResponse = sqlH.getDTResponseTableData(dyIndex.getDtBasicCode(), dyIndex.getcCode(), dyIndex.getDonorCode(), dyIndex.getAwardCode(), dyIndex.getProgramCode(), getStaffID(), mDTQTable.getDtQCode(), mDTATables.get(0).getDt_ACode(), mDTRSeq);

        countChecked = 0;
        if (dataType != null) {
            switch (responseControl) {
                case TEXT_BOX:


                    if (!resLupText.equals(GPS_LATD) && !resLupText.equals(GPS_LONG) && !resLupText.equals(GPS_COORDINATE)) {         // if response mode is not Latitude

                        dt_edt.setVisibility(View.VISIBLE);
                        switch (dataType) {
                            case TEXT:
                                dt_edt.setHint("Text");
                                dt_edt.setInputType(InputType.TYPE_CLASS_TEXT);
                                break;
                            case NUMBER:

//                            dt_edt.setHint("Number");
//                            dt_edt.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
                                // all number dec format

                                if (resLupText.equals("Number (not decimal)"))
                                    dt_edt.setInputType(InputType.TYPE_CLASS_NUMBER);
                                else
                                    dt_edt.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);

                                break;

                        }                                                                           // end of switch


                        if (dtResponse != null)                                                     // if data exist in db show data
                            dt_edt.setText(dtResponse.getDtaValue());
                        else
                            dt_edt.setText("");

                    }                                                                               // end of if
                    else {
                        loadGpsLatLong(resLupText, dtResponse);

                    } // end of else

                    break;


                case Date_OR_Time:

                    _dt_tv_DatePickerNLatLong.setVisibility(View.VISIBLE);

                    /**
                     * if data exists show data
                     */
                    if (dtResponse != null)
                        _dt_tv_DatePickerNLatLong.setText(dtResponse.getDtaValue());
                    else
                        _dt_tv_DatePickerNLatLong.setText("Select Date");


                    switch (dataType) {                                                             //specification  of date time or only time
                        case DATE_TIME:
                            getTimeStamp(_dt_tv_DatePickerNLatLong);
                            break;
                        case DATE:

                            _dt_tv_DatePickerNLatLong.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    setDate();
                                }
                            });
                            break;
                    }


                    break;

                case COMBO_BOX:
                    dt_spinner.setVisibility(View.VISIBLE);


                    hideSoftKayPad(dt_spinner);                             //hide the key pad when spinner Appears


                    if (dtResponse != null)                                 //if data exist get the Spinner String  set position •
                        strSpinner = dtResponse.getDtaValue();
                    else
                        strSpinner = null;
                    loadDynamicSpinnerList(dyIndex.getcCode(), resLupText);


                    break;
                case CHECK_BOX:

                    parent_layout_onlyFor_CB.setVisibility(View.VISIBLE);


                    hideSoftKayPad(dt_spinner);                             //hide the key pad when spinner Appears

                    if (mDTATables.size() > 0)
                        loadDynamicCheckBox(mDTATables);
                    restoreCheckBoxPreviousValue(mCheckBox_List);


                    break;
                case RADIO_BUTTON:
                    mRadioGroup.setVisibility(View.VISIBLE);

                    //hide the key pad when spinner Appears
                    hideSoftKayPad(dt_spinner);

                    if (mDTATables.size() > 0)
                        loadRadioButtons(mDTATables);

                    // restore the previous value from db
                    restoreRadioButtonPreviousValue(mRadioGroup, mRadioButton_List);


                    break;
                case RADIO_BUTTON_N_TEXTBOX:
                    dt_layout_Radio_N_EditText.setVisibility(View.VISIBLE);

                    if (mDTATables.size() > 0)
                        loadRadioButtonAndEditText(mDTATables, dataType);

                    // restore the previous value from db
                    restoreRadioButtonPreviousValue(mRadioGroupForRadioAndEditText, mRadioButtonForRadioAndEdit_List);
                    break;
                case CHECKBOX_N_TEXTBOX:
                    parent_layout_FOR_CB_N_ET.setVisibility(View.VISIBLE);

                    if (mDTATables.size() > 0)
                        loadDynamicCheckBoxAndEditText(mDTATables, dataType);

                    restoreCheckBoxPreviousValue(mCheckBoxForCheckBoxAndEdit_List);
                    break;
                case PHOTO:
                    loadDynamicPhoto();
                    break;

            }                                               // end of switch
        }                                                   // end of if

    }                                                       //  end of loadDT_QResMode


    /**
     * Check All type of Validation For
     */
    private void saveProcessValidation() {

        boolean calling4TerminalPoint = false;

        int i = 0;
        String responseControl = mDTQResMode.getDtResponseValueControl();
        String resLupText = mDTQResMode.getDtQResLupText();

        if (mDTQTable == null)
            return;

        if (mDTQTable.getAllowNullFlag() != null && mDTQTable.getAllowNullFlag().equals("N")) {

            switch (responseControl) {
                case TEXT_BOX:
                    if (!resLupText.equals(GPS_LATD) && !resLupText.equals(GPS_LONG) && !resLupText.equals(GPS_COORDINATE)) {         // if response mode is not Latitude

                        String edtInput = dt_edt.getText().toString();

                        if (edtInput.equals("Text") || edtInput.equals("Number") || edtInput.length() == 0) {
                            errorIndicator();
                            displayError("Insert  Text");
                        } else {


                            //  if DTA type is number then it gonna check the max & min Value
                            if (mDTATables.get(0).getDataType().equals("Number")) {

                                // comparing the highest input value an  lowest value
                                String max = mDTATables.get(0).getMaxValue();
                                String min = mDTATables.get(0).getMinValue();
                                if (((max == null || max.length() == 0) || (min == null || min.length() == 0)) || (Double.parseDouble(edtInput) <= Parse.StringToDoubleNullCheck(max)) && (Double.parseDouble(edtInput) >= Parse.StringToDoubleNullCheck(min))) {
                                    normalIndicator();
                                    saveData(edtInput, "", mDTATables.get(0), calling4TerminalPoint);

                                    // load next Question
                                    getNextQuestion();
                                } else {
                                    errorIndicator();
                                    displayError("Out of range input! ");
                                }
                            } else {                                                // else the input method would  be the text
                                normalIndicator();
                                saveData(edtInput, "", mDTATables.get(0), calling4TerminalPoint);

                                getNextQuestion();                                               // load next Question
                            }


                        }

                    } else {
                        normalIndicator();

                        /**                          {@link #mDTATables} wil be single                         */
                        saveData(_dt_tv_DatePickerNLatLong.getText().toString(), "", mDTATables.get(0), calling4TerminalPoint);
                        getNextQuestion();
                    }


                    break;
                case Date_OR_Time:

                    String dateTime = _dt_tv_DatePickerNLatLong.getText().toString();
                    if (dateTime.equals("Click for Date") || dateTime.equals("Select Date")) {
                        errorIndicator();
                        displayError("Set Date First");

                    } else {
                        normalIndicator();

                        /**                         * mDTATables.get(0) wil be single                         */
                        saveData(_dt_tv_DatePickerNLatLong.getText().toString(), "", mDTATables.get(0), calling4TerminalPoint);
                        getNextQuestion();
                    }
                    break;
                case COMBO_BOX:


                    if (idSpinner != null) {            // here it get null point reference if spinner get no values
                        if (idSpinner.equals("00")) {

                            errorIndicator();
                            displayError("Select Item");

                        } else {
                            normalIndicator();

                            // set id
                            saveData(idSpinner, "", mDTATables.get(0), calling4TerminalPoint);
//                            Toast.makeText(mContext, "idSpinner : "+idSpinner, Toast.LENGTH_SHORT).show();


                            getNextQuestion();       // load  next question
                        }
                    }
                    break;
                case CHECK_BOX:

                    if (countChecked <= 0) {
                        errorIndicator();
                        displayError("Select a option.");

                    } else {
                        normalIndicator();
                        /**
                         *check the combination in ascending order and create the combination separated by commas
                         */
                        String dtaCombinationCode = "";
                        i = 0;
                        for (CheckBox cb : mCheckBox_List) {

                            if (cb.isChecked()) {
                                dtaCombinationCode = dtaCombinationCode + mDTATables.get(i).getDt_AValue() + ",";
                                saveData("", "", mDTATables.get(i), calling4TerminalPoint);

                            }       // end of if condition

                            i++;
                        }           // end of for each loop


                        dtaCombinationCode = dtaCombinationCode.substring(0, dtaCombinationCode.length() - 1);  //remove the last character from a string

                        // skip rules for test
                        skipRules(responseControl, i, dtaCombinationCode);
                        // for test  u may delete the below code
//                        Toast.makeText(mContext, "dtaCombinationCode: " + dtaCombinationCode, Toast.LENGTH_SHORT).show();

                        getNextQuestion();       // load  next question
                    }// end of else


                    break;

                case RADIO_BUTTON:

                    if (mRadioGroup.getCheckedRadioButtonId() == -1) {
                        errorIndicator();
                        displayError("Select a option.");
                    } else {
                        i = 0;
                        for (RadioButton rb : mRadioButton_List) {
                            if (rb.isChecked()) {

                                skipRules(responseControl, i, "");

                                saveData("", "", mDTATables.get(i), calling4TerminalPoint);
                                break;
                            }
                            i++;
                        }
                        getNextQuestion();
                    }


                    break;


                case RADIO_BUTTON_N_TEXTBOX:

                    boolean error = false;

                    i = 0;
                    for (RadioButton rb : mRadioButtonForRadioAndEdit_List) {
                        if (rb.isChecked()) {

                            if (mEditTextForRadioAndEdit_List.get(i).getText().length() == 0) {
                                errorIndicator();
                                displayError("Insert value for Selected Option");
                                error = true;
                                break;

                            } else {
                                normalIndicator();

                                saveData(mEditTextForRadioAndEdit_List.get(i).getText().toString(), "", mDTATables.get(i), calling4TerminalPoint);
                            }
                        }
                        i++;    //increment
                    }

                    if (!error)
                        getNextQuestion();
                    break;

                case CHECKBOX_N_TEXTBOX:


                    normalIndicator();
                    int k = 0;
                    for (CheckBox cb : mCheckBoxForCheckBoxAndEdit_List) {
                        if (cb.isChecked()) {
                            Toast.makeText(mContext, "Radio Button no:" + (k + 1) + " is checked"
                                    + " the value of the : " + mEditTextForCheckBoxAndEdit_List.get(k).getText(), Toast.LENGTH_SHORT).show();
                            if (mEditTextForCheckBoxAndEdit_List.get(k).getText().length() == 0) {
                                errorIndicator();
                                displayError("Insert value for Selected Option");
                                break;
                            } else {
                                normalIndicator();
                                saveData(mEditTextForCheckBoxAndEdit_List.get(k).getText().toString(), "", mDTATables.get(k), calling4TerminalPoint);
                            }


                        }
                        k++;
                    }
                    getNextQuestion();
                    break;
                case PHOTO:

                    if (getImageString() == null) {
                        errorIndicator();
                        displayError("Insert  Image");
                    } else {
                        saveData("", getImageString(), mDTATables.get(i), calling4TerminalPoint);
                        normalIndicator();
                        getNextQuestion();

                    }
                    break;


            }// end of switch

        }// end of the AllowNullFlag
        else {


            /**
             *  TODO: 9/29/2016  save method & update method for allow null respose
             *  if AllowNullFlag is true
             *  means when user can access
             */
//                    saveData("");


            //NEXT QUESTION
            getNextQuestion();


        }// end of else where ans is not magneto


    }

    /**
     * skip Rule
     * used in {@link #saveProcessValidation()}
     *
     * @param indexOfControl it can be radio button or check box index
     */

    private void skipRules(String controlType, int indexOfControl, String value) {

        //ekhane control type  check kore skipe rule tta deoya  hoy
        // this block chek the control type then determine the skipe rules for controls
        switch (controlType) {
            case RADIO_BUTTON:

                // initialize  mSkipDTATable
                mSkipDTATable = null;

                //  get index of radio button which is clicked by user
                // and check  for skip rules . does it have any dt_SkipDTQCode
                String dt_SkipDTQCode = mDTATables.get(indexOfControl).getDt_SkipDTQCode();

                if (dt_SkipDTQCode == null || dt_SkipDTQCode.equals("null")) {

                    // if get not value of dt combination then next ques goes by indexing .
                    isSkipQuestion = false;
                    mSkipDTATable = null;
                } else {
                    if (!dt_SkipDTQCode.equals("null") && dt_SkipDTQCode.length() > 4) {
                        if (!isSkipQuestion) {
                            isSkipQuestion = true;
                        }

                        // get DTA value
                        mSkipDTATable = mDTATables.get(indexOfControl);

                    }
                }
                break;
            case CHECK_BOX:

                // initialize  mSkipDTATable to null
                mSkipDTATable = null;
                String dtASkipDTQCode = sqlH.getDTSkipDTQCde(mDTQTable.getDtBasicCode(), mDTQTable.getDtQCode(), value);
                if (!dtASkipDTQCode.equals("null") && !dtASkipDTQCode.equals("") && dtASkipDTQCode.length() > 4) {
                    if (!isSkipQuestion) {
                        isSkipQuestion = true;
                    }

                    // set only dt skip DTQ Code
                    mSkipDTATable = new DT_ATableDataModel();
                    mSkipDTATable.setDt_SkipDTQCode(dtASkipDTQCode);
                } else {

                    // if get not value of dt combination then next ques goes by indexing .
                    isSkipQuestion = false;
                    mSkipDTATable = null;
                }

                break;
        }


    }


    /**
     * this method show the error dialog
     *
     * @param errorMsg Massage In valid
     */
    private void displayError(String errorMsg) {
        dialogManager.showWarningDialog(mContext, errorMsg);
    }


    /**
     * @param ansValue    answer value
     * @param imageString image String base64 Format
     * @param dtATable    dynamic Answer table
     */
    private void saveData(String ansValue, String imageString, DT_ATableDataModel dtATable, boolean isCall4termPoint) {
        saveOnResponseTable(ansValue, imageString, dtATable, isCall4termPoint);


    }

    /**
     * the freeze point start here .
     * the {@link #mBufferingList List will temporary save the value  of saved data
     * untill {@link #mFreezeTerminatedIndex =-1 } or @link {@link #isFreeze = true }
     * }
     */
    private void saveTemporaryDataIntoMBufferList(int mQusIndex, FreezeDataModel freezingData, boolean isCall4termPoint) {
        if (!isCall4termPoint && (mFreezeTerminatedIndex == -1 || mQusIndex == mFreezeTerminatedIndex)) {
            mBufferingList.add(freezingData);
        }
    }

    /**
     * @param ansValue user input
     * @param dtATable DTA Table
     */
    /**
     * insert update operation . don't change any code with out consulting
     *
     * @param ansValue    user input / spinner value / radio button code / edit text values
     * @param imageString base 64 image string
     * @param dtATable    dynamic Answer table module
     */

    private void saveOnResponseTable(String ansValue, String imageString, DT_ATableDataModel dtATable, boolean isCall4termPoint) {


        String DTBasic = dyIndex.getDtBasicCode();
        String AdmCountryCode = dyIndex.getcCode();
        String AdmDonorCode = dyIndex.getDonorCode();
        String AdmAwardCode = dyIndex.getAwardCode();
        String AdmProgCode = dyIndex.getProgramCode();
        String DTEnuID = getStaffID();

        String DTQCode = mDTQTable.getDtQCode();
        String DTQText = mDTQTable.getqText();
        String DTACode = dtATable.getDt_ACode();
        /**    DTRSeq is user input serial no         */
        int DTRSeq = mDTRSeq;
        String DTAValue = null;
        String ProgActivityCode = dyIndex.getProgramActivityCode();
        String DTTimeString = null;
        try {
            DTTimeString = getDateTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String OpMode = dyIndex.getOpMode();
        String OpMonthCode = dyIndex.getOpMonthCode();

        String DataType = dtATable.getDataType();


        DTAValue = dtATable.getDt_AValue() == null || dtATable.getDt_AValue().equals("null") || dtATable.getDt_AValue().length() == 0 ? ansValue : dtATable.getDt_AValue();

        /**
         *  get data freeze data         *
         */
        FreezeDataModel freezingData = new FreezeDataModel(AdmAwardCode, AdmCountryCode, AdmDonorCode, AdmProgCode, DataType, DTACode, DTAValue
                , DTBasic, DTEnuID, DTQCode, DTQText, DTRSeq, DTTimeString, mQusIndex, OpMode, OpMonthCode, ProgActivityCode);

        saveTemporaryDataIntoMBufferList(mQusIndex, freezingData, isCall4termPoint);

        /**
         * main execute   Insert or update operation
         */
        if (sqlH.isDataExitsInDTAResponse_Table(DTBasic, AdmCountryCode, AdmDonorCode, AdmAwardCode, AdmProgCode, DTEnuID, DTQCode, DTACode, mDTRSeq)) {
            sqlH.updateIntoDTResponseTable(DTBasic, AdmCountryCode, AdmDonorCode, AdmAwardCode, AdmProgCode, DTEnuID, DTQCode, DTACode,
                    String.valueOf(DTRSeq), DTAValue, ProgActivityCode, DTTimeString, OpMode, OpMonthCode, DataType, imageString);
            sqlH.updateIntoDTSurveyTable(DTBasic, AdmCountryCode, AdmDonorCode, AdmAwardCode, AdmProgCode, DTEnuID, DTQCode, DTACode,
                    String.valueOf(DTRSeq), DTAValue, ProgActivityCode, DTTimeString, OpMode, OpMonthCode, DataType, DTQText, surveyNumber);

        } else {


            sqlH.addIntoDTResponseTable(DTBasic, AdmCountryCode, AdmDonorCode, AdmAwardCode, AdmProgCode, DTEnuID, DTQCode, DTACode, String.valueOf(DTRSeq), DTAValue, ProgActivityCode, DTTimeString, OpMode, OpMonthCode, DataType, imageString, true);
            sqlH.addIntoDTSurveyTable(DTBasic, AdmCountryCode, AdmDonorCode, AdmAwardCode, AdmProgCode, DTEnuID, DTQCode, DTACode, String.valueOf(DTRSeq), DTAValue, ProgActivityCode, DTTimeString, OpMode, OpMonthCode, DataType, DTQText, surveyNumber, imageString,mDTQResMode.getDtQResLupText());

            Log.i(TAG, "DTBasic :" + DTBasic + " AdmCountryCode: " + AdmCountryCode + " AdmDonorCode: " + AdmDonorCode + " AdmAwardCode: " + AdmAwardCode + " AdmProgCode:" + AdmProgCode + " DTEnuID: " + DTEnuID + " DTQCode: " + DTQCode + " DTACode: " + DTACode + " DTRSeq: " + String.valueOf(DTRSeq) + " DTAValue:" + DTAValue
                    + " ProgActivityCode :" + ProgActivityCode + " DTTimeString:" + DTTimeString + " OpMode: " + OpMode + "OpMonthCode :" + OpMonthCode + " DataType: " + DataType + " imageString :" + imageString);
        }


    }


    /**
     * this method  delete the unfinished  or incomplete response data form the
     *
     * @param dtBasicCode   Dynamic Table  Basic Code
     * @param cCode         Adm Country Code
     * @param donorCode     Adm Donor Code
     * @param awardCode     Adm Award Code
     * @param progCode      Adm Program Code
     * @param OpMode        Op Code
     * @param opMonthCode   op Month Code
     * @param DTEnuID       Dt Eliminator Id
     * @param DTRSeq        Dynamic Table Response Sequence
     * @param sqLiteHandler sqLiteHandler
     */

    public static void deleteFromResponseTable(String dtBasicCode, String cCode, String donorCode, String awardCode, String progCode, String OpMode, String opMonthCode, String DTEnuID, int DTRSeq, SQLiteHandler sqLiteHandler) {

        /**         *  total Question no is less then index no
         *  the Delete Syntax in  the {@link SQLiteHandler#deleteFromDTResponseTable(String, String, String, String, String, String, int, String, String)}         */


        if (dtBasicCode != null && cCode != null && donorCode != null && awardCode != null && progCode != null && DTEnuID != null && DTRSeq != 0)
            sqLiteHandler.deleteFromDTResponseTable(dtBasicCode, cCode, donorCode, awardCode, progCode, DTEnuID, DTRSeq, OpMode, opMonthCode);

    }


    /**
     * set thank you statements to user to understand end set survey
     * use {@link SQLServerSyntaxGenerator#sqlSpDTShortName_Save()} sp
     */
    private void compilationFunctionNMessage() {

        //  don't delete below code
        //saveProcessValidation();
        hideViews();
        setTextInQuestionTextView("Thank you .");

        // when all the saved complete generate as Sp
        SQLServerSyntaxGenerator mSyntaxGenerator = new SQLServerSyntaxGenerator();
        mSyntaxGenerator.setDTBasic(dyIndex.getDtBasicCode());
        mSyntaxGenerator.setDtShortName(dyIndex.getDtShortName());
        mSyntaxGenerator.setAdmCountryCode(dyIndex.getcCode());
        mSyntaxGenerator.setAdmDonorCode(dyIndex.getDonorCode());
        mSyntaxGenerator.setAdmAwardCode(dyIndex.getAwardCode());
        mSyntaxGenerator.setAdmProgCode(dyIndex.getProgramCode());
        mSyntaxGenerator.setOpMonthCode(dyIndex.getOpMonthCode());
        mSyntaxGenerator.setDTEnuID(getStaffID());
        sqlH.insertIntoUploadTable(mSyntaxGenerator.sqlSpDTShortName_Save());

        Toast.makeText(mContext, "Saved Successfully ", Toast.LENGTH_SHORT).show();

        /// Bellow Code end the
        addStopIconButton(btnNextQues);
    }

    /**
     * if present question has skip Code then this method check
     *
     * @param quesIndex index of the question
     * @return DTQTableDataModel
     */
    private DTQTableDataModel skipToQuestionCheck(final int quesIndex) {

        DTQTableDataModel dtqTableDataModel = null;
        String dt_SkipDTQCode = "";
        if (mSkipDTATable != null && isSkipQuestion) {
            dt_SkipDTQCode = mSkipDTATable.getDt_SkipDTQCode();

            dt_SkipDTQues = loadNextQuestion(dyIndex.getDtBasicCode(), dt_SkipDTQCode);     // get the skip question

            if (dt_SkipDTQues != null) {                                                //  safety block
                dtqTableDataModel = dt_SkipDTQues;
                isSkipQuestion = false;

                mQusIndex = Integer.parseInt(dt_SkipDTQues.getqSeq());                  // set question index
            }

        } else {
            dtqTableDataModel = loadNextQuestion(dyIndex.getDtBasicCode(), quesIndex);

        }


        return dtqTableDataModel;
    }

    /**
     * used in {@link #getNextQuestion()}
     * ei method er kaj hoilo oi qustion re dekhte dibe na .
     * Note : don't use this method  & don't delete this method
     *
     * @param nextQus dt next question
     */
    private void skipQuestion(DTQTableDataModel nextQus) {
//        String dt_SkipDTQCode = "";
//        if (mSkipDTATable != null && isSkipQuestion) {
//            dt_SkipDTQCode = mSkipDTATable.getDt_SkipDTQCode();
//            //get the skip question
//            dt_SkipDTQues = loadNextQuestion(dyIndex.getDtBasicCode(), dt_SkipDTQCode);
//
//            if (dt_SkipDTQues != null) {
//                if (dt_SkipDTQues.getqText().equals(nextQus.getqText()))
//                    return;
//            }
//
//
//        }
    }

    /**
     * This method show a dialog to ask user to collect the survey data again.
     * If user press yes then  this method  will invoke the
     * {@link #initialWithFirstQues()} to start to collect response again.
     * For continuing to a new question series, no need to show the previous image. So I just make the global variable {@link #mPhotoBitmap} null
     */
    private void continueDialog() {

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(mContext);
        alertDialog.setTitle("Continue !!");

        // On pressing Settings button
        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

                continueProcess();


            }
        });

        // Setting Dialog Message
        alertDialog.setMessage("Do you want to continue ?");

        // on pressing cancel button
        alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                goToMainActivity((Activity) mContext);
            }
        });

        // Showing Alert Message
        alertDialog.show();
    }

    private void continueProcess() {

        // check the bitmap is null or not otherwise recycle() method invoked the virtual
        //  method , which cause the null point exception
        if (mPhotoBitmap != null)
            mPhotoBitmap.recycle();
        mPhotoBitmap = null;

        // set skip Question flag false
        isSkipQuestion = false;
        // the stop icon
        removeStopIconNextButton(btnNextQues);
        /**
         * if freeze or {@link DTResponseRecordingActivity#isFreeze = false }  button is not
         * selected then question will appears from sstatingpoint
         */
        if (!isFreeze)
            initialWithFirstQues();
        else {

            // initiate the question index
            mQusIndex = 1;

            // get the new survey sequence number
            getSurveySequenceNumber();
            for (int i = 0; i < mBufferingList.size(); i++) {
                String msg = "";

                // set new sequence number   to the old values
                mBufferingList.get(i).setDTRSeq(mDTRSeq);


                String DTBasic = mBufferingList.get(i).getDTBasic();
                String AdmCountryCode = mBufferingList.get(i).getAdmCountryCode();
                String AdmDonorCode = mBufferingList.get(i).getAdmDonorCode();
                String AdmAwardCode = mBufferingList.get(i).getAdmAwardCode();
                String AdmProgCode = mBufferingList.get(i).getAdmProgCode();
                String DTEnuID = mBufferingList.get(i).getDTEnuID();
                String DTQCode = mBufferingList.get(i).getDTQCode();
                String DTACode = mBufferingList.get(i).getDTACode();
                String DTAValue = mBufferingList.get(i).getDTAValue();
                String ProgActivityCode = mBufferingList.get(i).getProgActivityCode();
                String DTTimeString = mBufferingList.get(i).getDTTimeString();
                String OpMode = mBufferingList.get(i).getOpMode();
                String OpMonthCode = mBufferingList.get(i).getOpMonthCode();
                String DataType = mBufferingList.get(i).getDataType();
                String DTQText = mDTQTable.getqText();
                String imageString = "";
                int DTRSeq = mBufferingList.get(i).getDTRSeq();
                if (i >= 1) {
                    String presentQuesCode = mBufferingList.get(i).getDTQCode();
                    String previousQuesCode = mBufferingList.get(i - 1).getDTQCode();

                    // it's all about  check box
                    if (!presentQuesCode.equals(previousQuesCode)) {
                        sqlH.addIntoDTResponseTable(DTBasic, AdmCountryCode, AdmDonorCode, AdmAwardCode, AdmProgCode, DTEnuID, DTQCode, DTACode, String.valueOf(DTRSeq), DTAValue, ProgActivityCode, DTTimeString, OpMode, OpMonthCode, DataType, "", true);
                        sqlH.addIntoDTSurveyTable(DTBasic, AdmCountryCode, AdmDonorCode, AdmAwardCode, AdmProgCode, DTEnuID, DTQCode, DTACode, String.valueOf(DTRSeq), DTAValue, ProgActivityCode, DTTimeString, OpMode, OpMonthCode, DataType, DTQText, surveyNumber, imageString,mDTQResMode.getDtQResLupText());

                        // uses suffix in increments
                        mQusIndex++;
                    }
                } else if (i == 0) {
                    // saved the first freeze question
                    sqlH.addIntoDTResponseTable(DTBasic, AdmCountryCode, AdmDonorCode, AdmAwardCode, AdmProgCode, DTEnuID, DTQCode, DTACode, String.valueOf(DTRSeq), DTAValue, ProgActivityCode, DTTimeString, OpMode, OpMonthCode, DataType, "", true);
                    sqlH.addIntoDTSurveyTable(DTBasic, AdmCountryCode, AdmDonorCode, AdmAwardCode, AdmProgCode, DTEnuID, DTQCode, DTACode, String.valueOf(DTRSeq), DTAValue, ProgActivityCode, DTTimeString, OpMode, OpMonthCode, DataType, DTQText, surveyNumber, imageString,mDTQResMode.getDtQResLupText());

                }


            }

            // set question index to zero
            getNextQuestion();

        }
    }


    /**
     * @param qusObject DTQTable object
     *                  {@link #mDTATables} must be assigned before invoking {@link #loadDT_QResMode(String)}
     *                  {@link #mDTATables} needed in {@link #saveData(String, String, DT_ATableDataModel, boolean)}   method
     */

    private void displayQuestion(DTQTableDataModel qusObject) {


        /**         * set question in {@link #tv_DtQuestion}         */
        setTextInQuestionTextView(qusObject.getqText());

        // get Dynamic table Answer mode
        mDTATables = sqlH.getDTA_Table(qusObject.getDtBasicCode(), qusObject.getDtQCode());

        /**         * {@link #mDTATables} if it's size is zero than there will be IndexOutOfBoundsException
         *          occur  the poxy data prevent to occur that Exception
         */
        if (mDTATables.size() == 0) {
            DT_ATableDataModel proxyDATA_data = new DT_ATableDataModel(mDTQTable.getDtBasicCode(), mDTQTable.getDtQCode(), "null", "No Recoded in DB", "null", "null", "null", "null", "null", "null", "N", "null", "null", "Text", "null");
            mDTATables.add(proxyDATA_data);
        }

        loadDT_QResMode(qusObject.getqResModeCode());

    }

    /**
     * @param quesText Question String
     */
    private void setTextInQuestionTextView(String quesText) {
        tv_DtQuestion.setText(quesText);
    }


    /**
     * this method load the first question
     */
    private void initialWithFirstQues() {

        // if 1st question appears  then remove the previous button
        btnPreviousQus.setVisibility(View.INVISIBLE);

        // set question index to zero
        mQusIndex = 1;

        getSurveySequenceNumber();

        // hide the view
        hideViews();

        DTQTableDataModel qus = fistQuestion(dyIndex.getDtBasicCode());
        displayQuestion(qus);
    }

    /**
     * this method go to the db check the last survey number then add by 1
     * create new number for records
     */
    private void getSurveySequenceNumber() {
        surveyNumber = sqlH.getSurveyNumber(dyIndex.getDtBasicCode(), dyIndex.getcCode(), dyIndex.getDonorCode(), dyIndex.getAwardCode(), dyIndex.getProgramCode(), getStaffID());
        mDTRSeq = sqlH.getNextDTResponseSequence(dyIndex.getDtBasicCode(), dyIndex.getcCode(), dyIndex.getDonorCode(), dyIndex.getAwardCode(), dyIndex.getProgramCode(), getStaffID());

    }

    /**
     * Hide the dynamic views
     */
    private void hideViews() {
        _dt_tv_DatePickerNLatLong.setVisibility(View.GONE);
        dt_edt.setVisibility(View.GONE);
        dt_spinner.setVisibility(View.GONE);
        parent_layout_onlyFor_CB.setVisibility(View.GONE);
        mRadioGroup.setVisibility(View.GONE);
        dt_layout_Radio_N_EditText.setVisibility(View.GONE);
        parent_layout_FOR_CB_N_ET.setVisibility(View.GONE);
        dt_photo.setVisibility(View.GONE);
    }


    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void addStopIconButton(Button button) {
        button.setText("");
        Drawable imageHome = getResources().getDrawable(R.drawable.stop);
        button.setCompoundDrawablesRelativeWithIntrinsicBounds(imageHome, null, null, null);
        setPaddingButton(mContext, imageHome, button);
    }


    /**
     * This method is used for  hiding the soft keypad.
     * parameter will take the view which is shown right now.
     * .Note : this method must be called after view is Visible , otherwise it exception
     *
     * @param view button /spinner/ check box / Edit text /Text View any thing
     */
    public void hideSoftKayPad(View view) {
        InputMethodManager imm = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }


    /**
     * this method set the error point
     *
     * @param button either {@link #btnPreviousQus} or {{@link #btnNextQues}}
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void removeStopIconNextButton(Button button) {
        button.setText("");
        Drawable imageHome;
        if (button == btnPreviousQus)
            imageHome = getResources().getDrawable(R.drawable.goto_back);
        else
            imageHome = getResources().getDrawable(R.drawable.goto_forward);

        button.setCompoundDrawablesRelativeWithIntrinsicBounds(imageHome, null, null, null);
        setPaddingButton(mContext, imageHome, button);
    }


    /**
     * the method invoked once  in {@link  #onCreate(Bundle)}
     *
     * @param dtBasic dtBasic code as primary key
     * @return Ques object  of first index
     */
    private DTQTableDataModel fistQuestion(final String dtBasic) {
        return loadQuestion(dtBasic, 1);
    }

    /**
     * this method load the next question
     * invoking in {@link #btnNextQues}
     *
     * @param dtBasic  dtBasic code as primary key
     * @param qusIndex Qus  index
     * @return Ques object  of given index
     */
    private DTQTableDataModel loadNextQuestion(final String dtBasic, final int qusIndex) {
        return loadQuestion(dtBasic, qusIndex);
    }

    private DTQTableDataModel loadNextQuestion(final String dtBasic, final String dtqCode) {
        return loadQuestion(dtBasic, dtqCode);
    }

    /**
     * invoking in {@link #btnPreviousQus}
     *
     * @param dtBasic  dtBasic code as primary key
     * @param qusIndex Qus  index
     * @return Ques object  of given index
     */

    private DTQTableDataModel loadPreviousQuestion(final String dtBasic, final int qusIndex) {

        return loadQuestion(dtBasic, qusIndex);
    }

    /**
     * this method get single  question  of Dynamic table  with respect to Dt Basic Code and
     * sequence index (ascending order ) one by one .
     *
     * @param dtBasic  dtBasic Code
     * @param qusIndex question Index
     * @return {@link DTQTableDataModel }
     */
    public DTQTableDataModel loadQuestion(final String dtBasic, final int qusIndex) {
        mDTQTable = sqlH.getSingleDynamicQuestion(dtBasic, qusIndex);
        return mDTQTable;
    }


    public DTQTableDataModel loadQuestion(final String dtBasic, final String dtQuestionCode) {
        mDTQTable = sqlH.getSingleDynamicQuestion(dtBasic, dtQuestionCode);
        return mDTQTable;
    }


    private void loadGpsLatLong(String resLupText, DTResponseTableDataModel dtResponse) {

        _dt_tv_DatePickerNLatLong.setVisibility(View.VISIBLE);

        _dt_tv_DatePickerNLatLong.setText("");
        _dt_tv_DatePickerNLatLong.setClickable(false);

        if (dtResponse != null)                                                 //if data exists show data
            _dt_tv_DatePickerNLatLong.setText(dtResponse.getDtaValue());
        else {
            LocationManager manager = (LocationManager)
                    getSystemService(Context.LOCATION_SERVICE);

            mLocation = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);     //for demo, getLastKnownLocation from GPS only, not from NETWORK


            manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, mLocationListener);  // get the update of location
            if (mLocation != null) {

                String latLong = "";

                latLong = String.valueOf(mLocation.getLongitude()) + " , " + String.valueOf(mLocation.getLatitude());
//                if (resLupText.equals(GPS_LATD))
//                latLong = String.valueOf(mLocation.getLatitude());
//                else


                _dt_tv_DatePickerNLatLong.setText(latLong);
            } else {
                dialogManager.showInfromDialog(mContext, "Turn On", "Turn on the GPS !");
            }

        } // end of data exits else
    }

    /**
     * this method restore the check box stage from fetching data
     *
     * @param checkBoxList global check box list array
     */
    private void restoreCheckBoxPreviousValue(List<CheckBox> checkBoxList) {
        List<DTResponseTableDataModel> list = sqlH.getCheckBoxesDTResponseTableData(dyIndex.getDtBasicCode(), dyIndex.getcCode(), dyIndex.getDonorCode(), dyIndex.getAwardCode(), dyIndex.getProgramCode(), getStaffID(), mDTQTable.getDtQCode(), mDTRSeq);

        if (list.size() > 0) {

            for (int j = 0; j < list.size(); j++) {
                int i = 0;
                while (i < checkBoxList.size()) {

                    if (checkBoxList.get(i).getText().equals(list.get(j).getDtALabel()))
                        checkBoxList.get(i).setChecked(true);

                    ++i;
                }

            }


        }

    }

    /**
     * restore the previous stage of the radio button
     *
     * @param rdGroup  Radio Group
     * @param rdBLists Radio Button List
     */
    private void restoreRadioButtonPreviousValue(RadioGroup rdGroup, List<RadioButton> rdBLists) {
        DTResponseTableDataModel dtRadioResponse = sqlH.getRadioDTResponseTableData(dyIndex.getDtBasicCode(), dyIndex.getcCode(), dyIndex.getDonorCode(), dyIndex.getAwardCode(), dyIndex.getProgramCode(), getStaffID(), mDTQTable.getDtQCode(), mDTRSeq);

        if (dtRadioResponse != null) {

            int i = 0;
            while (i < rdGroup.getChildCount()) {
                if (i == Integer.parseInt(dtRadioResponse.getDtaCode()) - 1) {
                    RadioButton radioButton = rdBLists.get(i);
                    radioButton.setChecked(true);
                    break;
                }
                ++i;
            }

        }
    }

    /**
     * Handel the the mPhotoBitmap capture section and save into the db
     */
    private void loadDynamicPhoto() {
        dt_photo.setVisibility(View.VISIBLE);

        //hide the key pad when spinner Appears
        hideSoftKayPad(dt_spinner);
        resetImageView();
        dt_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPhotoCaptureDialog(CAMERA_REQUEST_1);
            }
        });

    }

    /**
     * reset image view with icons if {@link #mPhotoBitmap} is null . else
     */
    private void resetImageView() {
        if (mPhotoBitmap == null) {
            dt_photo.setImageDrawable(mContext.getResources().getDrawable(R.drawable.camera_icon));
        } else {
            dt_photo.setImageBitmap(mPhotoBitmap);
        }
    }

    private void showPhotoCaptureDialog(final int requestCode) {

        final CharSequence[] items = getResources().getStringArray(R.array.cameraOption_2);

        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(new ContextThemeWrapper(mContext, android.R.style.Theme_Holo_Light_Dialog));
        builder.setTitle("Photo:");
        builder.setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case CameraUtils.CAPTURED_IMAGE:
                        CameraUtils camera = new CameraUtils();
                        camera.captureImageAlert(DTResponseRecordingActivity.this, requestCode);

                        break;
//                    case CameraUtils.DELETE_IMAGE:
//                        // checkPhotoAvailability(CountryCode, GrpCode, subGrpCode, LocationCode, ContentCode);
//                        break;
                    case CameraUtils.CANCEL - 1:
                        imageCaptureOptionDialog.dismiss();
                        break;
                }
                imageCaptureOptionDialog.dismiss();
            }
        });
        imageCaptureOptionDialog = builder.create();
        imageCaptureOptionDialog.show();
    }


    /**
     * @param requestCode Request Code For Camera
     * @param resultCode  Result Code
     * @param data        data Image stream
     */

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST_1 && resultCode == RESULT_OK && data != null) {
            mPhotoBitmap = (Bitmap) data.getExtras().get("data");

            adjustImageView(mPhotoBitmap);
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            if (mPhotoBitmap != null)
                mPhotoBitmap.compress(Bitmap.CompressFormat.PNG, 99, stream);
            byte[] byteArray = stream.toByteArray();
            String base64 = Base64.encodeToString(byteArray, Base64.DEFAULT);
            base64 = base64.trim();
            setImageString(base64);

        }
    }


    /**
     * this method do some adjustment Over Image View With Photo
     *
     * @param bitmap mPhotoBitmap
     */
    private void adjustImageView(Bitmap bitmap) {
        dt_photo.getLayoutParams().height = ViewGroup.LayoutParams.MATCH_PARENT;
        dt_photo.getLayoutParams().width = ViewGroup.LayoutParams.MATCH_PARENT;
        dt_photo.setImageBitmap(bitmap);
    }

    /**
     * Shuvo vai
     *
     * @param dtA_Table_Data ans Mode
     */

    private void loadDynamicCheckBox(List<DT_ATableDataModel> dtA_Table_Data) {
        /**
         * If there are any Children in layout Container it will reMove
         * And the list of the Check Box {@link #mCheckBox_List} clear
         *
         */
        if (parent_layout_onlyFor_CB.getChildCount() > 0) {
            mCheckBox_List.clear();
            parent_layout_onlyFor_CB.removeAllViews();
        }


        for (int i = 0; i < dtA_Table_Data.size(); i++) {
            TableRow row = new TableRow(this);
            row.setId(i);
            LinearLayout.LayoutParams layoutParams = new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT);
            row.setLayoutParams(layoutParams);
            CheckBox checkBox = new CheckBox(this);
            checkBox.setOnCheckedChangeListener(DTResponseRecordingActivity.this);
            checkBox.setId(i);

            ///set Text label
            checkBox.setText(dtA_Table_Data.get(i).getDt_ALabel());

            // set check box is checked or not

            DTResponseTableDataModel loadAns = sqlH.getDTResponseTableData(dyIndex.getDtBasicCode(), dyIndex.getcCode(), dyIndex.getDonorCode(), dyIndex.getAwardCode(), dyIndex.getProgramCode(), getStaffID(), mDTQTable.getDtQCode(), dtA_Table_Data.get(i).getDt_ACode(), mDTRSeq);
            if (loadAns != null) {
                if (loadAns.getDtaValue().equals("Y")) {
                    checkBox.setChecked(true);
                }

            }

            row.addView(checkBox);
            /**             * {@link #btnNextQues} needed             */
            mCheckBox_List.add(checkBox);
            parent_layout_onlyFor_CB.addView(row);
        }

        String dt_SkipDTQCode;
        for (int i = 0; i < dtA_Table_Data.size(); i++) {
            dt_SkipDTQCode = dtA_Table_Data.get(i).getDt_SkipDTQCode();
            if (dt_SkipDTQCode != null && !dt_SkipDTQCode.equals("null") && dt_SkipDTQCode.length() > 4) {
                isSkipRullAssociated = true;
                break;
            } else {
                isSkipRullAssociated = false;
            }
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
        if (isChecked) {
            countChecked++; //              increase number of Selected Check box
        } else {
            countChecked--;//              decrease number of  Selected  Check box
        }
    }


    /**
     * Date & time Session
     */
    public void getCurrentDate() {
        Calendar calendar = Calendar.getInstance();
        String strDate = mFormat.format(calendar.getTime());
        displayDate(strDate);
    }

    private void displayDate(String strDate) {
        _dt_tv_DatePickerNLatLong.setText(strDate);
    }

    public void setDate() {

        // anonymous object
        new DatePickerDialog(mContext, datePickerListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    /**
     * date Time picker Listener
     * The Date Listener invoke in {@link #setDate()}
     */

    DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, monthOfYear);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateDate();
        }
    };

    public void updateDate() {
        displayDate(mFormat.format(calendar.getTime()));
    }


    /**
     * this method load spinner value for different dt basic table dynamicly
     *
     * @param cCode      Country Code
     * @param resLupText res lup
     */
    private void loadDynamicSpinnerList(final String cCode, final String resLupText) {

        SpinnerLoader.loadDynamicSpinnerListLoader(mContext, sqlH, dt_spinner, cCode, resLupText, strSpinner, mDTQTable, dyIndex);


        dt_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strSpinner = ((SpinnerHelper) dt_spinner.getSelectedItem()).getValue();
                idSpinner = ((SpinnerHelper) dt_spinner.getSelectedItem()).getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void loadRadioButtons(List<DT_ATableDataModel> radioButtonItemName) {

        if (mRadioGroup.getChildCount() > 0) {
            mRadioButton_List.clear();
            mRadioGroup.removeAllViews();
        }


        for (int i = 0; i < radioButtonItemName.size(); i++) {
            RadioButton rdbtn = new RadioButton(this);
            rdbtn.setId(i);
            rdbtn.setTextSize(24); // set text size

            rdbtn.setPadding(0, 10, 0, 10);     // set padding

            rdbtn.setText(radioButtonItemName.get(i).getDt_ALabel()); // set label
            // jodi nicher line ta na execute  kori tahole  ager qus er jei redio
            // button ta select kori pore question ta korte pari na
            if (i == 0)
                rdbtn.setChecked(true);


            mRadioGroup.addView(rdbtn);
            mRadioButton_List.add(rdbtn);

        }// end of for loop

        /**
         * check the question is skip rule associated
         */
        String dt_SkipDTQCode;
        for (int i = 0; i < radioButtonItemName.size(); i++) {
            dt_SkipDTQCode = radioButtonItemName.get(i).getDt_SkipDTQCode();
            if (dt_SkipDTQCode != null && !dt_SkipDTQCode.equals("null") && dt_SkipDTQCode.length() > 4) {
                isSkipRullAssociated = true;
                break;
            } else {
                isSkipRullAssociated = false;
            }
        }


    }
    /**
     * Radio - EditText & CheckBox - EditText
     */

    /**
     * @param List_DtATable
     */

    public void loadRadioButtonAndEditText(List<DT_ATableDataModel> List_DtATable, String dataType) {

        if (ll_editText.getChildCount() > 0) {
            mRadioButtonForRadioAndEdit_List.clear();
            mEditTextForRadioAndEdit_List.clear();
            mRadioGroupForRadioAndEditText.removeAllViews();
            ll_editText.removeAllViews();
        }


        for (int i = 0; i < List_DtATable.size(); i++) {
            String label = List_DtATable.get(i).getDt_ALabel();
            RadioButton rdbtn = new RadioButton(this);

            rdbtn.setId(i);
            rdbtn.setText(label); // set label
            // jodi nicher line ta na execute  kori tahole  ager qus er jei radio button
            // ta select kori pore question ta korte pari na
            if (i == 0)
                rdbtn.setChecked(true);

            rdbtn.setTextSize(24); // set text size
            rdbtn.setPadding(0, 10, 0, 10);     // set padding
            rdbtn.setOnCheckedChangeListener(DTResponseRecordingActivity.this);


            EditText et = new EditText(this);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            params.setMargins(0, 5, 0, 5);
            et.setLayoutParams(params);
            et.setHint(label);
            et.setId(i);

            // soft keyboard controller
            if (dataType.equals(NUMBER)) {
                et.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);

            }
            et.setBackgroundColor(Color.WHITE);


/**
 *
 * todo aad index after set DTRespose Sequn {@link #saveOnResponseTable(String, DT_ATableDataModel)}
 */
            DTResponseTableDataModel loadAns = sqlH.getDTResponseTableData(dyIndex.getDtBasicCode(), dyIndex.getcCode(), dyIndex.getDonorCode(), dyIndex.getAwardCode(), dyIndex.getProgramCode(), getStaffID(), mDTQTable.getDtQCode(), List_DtATable.get(i).getDt_ACode(), mDTRSeq);
            if (loadAns != null) {
                rdbtn.setChecked(true);
                String value = loadAns.getDtaValue();
                et.setText(value);
            }


            mRadioGroupForRadioAndEditText.addView(rdbtn);
            mRadioButtonForRadioAndEdit_List.add(rdbtn);

            ll_editText.addView(et);
            mEditTextForRadioAndEdit_List.add(et);

        }

        /**
         * check the question is skip rule associated
         */
        String dt_SkipDTQCode;
        for (int i = 0; i < List_DtATable.size(); i++) {
            dt_SkipDTQCode = List_DtATable.get(i).getDt_SkipDTQCode();
            if (dt_SkipDTQCode != null && !dt_SkipDTQCode.equals("null") && dt_SkipDTQCode.length() > 4) {
                isSkipRullAssociated = true;
                break;
            } else {
                isSkipRullAssociated = false;
            }
        }


    }

    /**
     * If there are any Children in layout Container it will reMove
     * And the list of the Check Box {@link #mEditTextForCheckBoxAndEdit_List}
     * and {@link #mCheckBoxForCheckBoxAndEdit_List }
     * clear
     */

    private void loadDynamicCheckBoxAndEditText(List<DT_ATableDataModel> List_DtATable, String dataType) {

        // clean the list if any child exists in #subParent_CB_layout_FOR_CB_N_ET layout
        if (subParent_CB_layout_FOR_CB_N_ET.getChildCount() > 0) {
            subParent_ET_layout_FOR_CB_N_ET.removeAllViews();
            subParent_CB_layout_FOR_CB_N_ET.removeAllViews();
            mCheckBoxForCheckBoxAndEdit_List.clear();
            mEditTextForCheckBoxAndEdit_List.clear();
        }

        for (int i = 0; i < List_DtATable.size(); i++) {

            String label = List_DtATable.get(i).getDt_ALabel();
            TableRow row = new TableRow(this);
            row.setId(i);
            LinearLayout.LayoutParams layoutParams = new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT);
            row.setLayoutParams(layoutParams);
            CheckBox checkBox = new CheckBox(this);
            checkBox.setOnCheckedChangeListener(DTResponseRecordingActivity.this);
            checkBox.setId(i);
            checkBox.setText(label); //  set Text label
            row.addView(checkBox);

            EditText et = new EditText(this);
            et.setHint(label);
            et.setId(i);


            // set soft keyboard type

            if (dataType.equals(NUMBER)) {
                et.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);

            }
            et.setBackgroundColor(Color.WHITE);

            /**
             * This snippets work for Check Box Well  but not for the radio button
             * todo aad index after set DTRespose Sequn {@link #saveOnResponseTable(String, DT_ATableDataModel)}
             */
            DTResponseTableDataModel loadAns = sqlH.getDTResponseTableData(dyIndex.getDtBasicCode(), dyIndex.getcCode(), dyIndex.getDonorCode(), dyIndex.getAwardCode(), dyIndex.getProgramCode(), getStaffID(), mDTQTable.getDtQCode(), List_DtATable.get(i).getDt_ACode(), mDTRSeq);
            if (loadAns != null) {
                checkBox.setChecked(true);
                String value = loadAns.getDtaValue();
                et.setText(value);
            }

            subParent_ET_layout_FOR_CB_N_ET.addView(et);
            /**             * {@link #btnNextQues} needed */

            mEditTextForCheckBoxAndEdit_List.add(et);
            subParent_CB_layout_FOR_CB_N_ET.addView(row);
            mCheckBoxForCheckBoxAndEdit_List.add(checkBox);
        }


        String dt_SkipDTQCode;
        for (int i = 0; i < List_DtATable.size(); i++) {
            dt_SkipDTQCode = List_DtATable.get(i).getDt_SkipDTQCode();
            if (dt_SkipDTQCode != null && !dt_SkipDTQCode.equals("null") && dt_SkipDTQCode.length() > 4) {
                isSkipRullAssociated = true;
                break;
            } else {
                isSkipRullAssociated = false;
            }
        }
    }

    /**
     * Shuvo
     * this method only show the System Current Time
     *
     * @param tv Text view For Show
     */

    private void getTimeStamp(TextView tv) {
        final Calendar c = Calendar.getInstance();
        java.util.Date currentLocalTime = c.getTime();
        DateFormat date = new SimpleDateFormat("HH:mm ", Locale.ENGLISH);
        String timeStamp = date.format(currentLocalTime);
        tv.setText(timeStamp);
    }


}

