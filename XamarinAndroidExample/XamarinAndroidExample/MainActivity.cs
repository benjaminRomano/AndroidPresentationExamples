using System;

using Android.App;
using Android.Content;
using Android.Runtime;
using Android.Views;
using Android.Widget;
using Android.OS;
using System.Collections.Generic;

namespace XamarinAndroidExample
{
	[Activity (Label = "Call Menu", MainLauncher = true, Icon = "@drawable/icon")]
	public class MainActivity : Activity
	{

		protected override void OnCreate (Bundle bundle) 
		{
			base.OnCreate(bundle);

			SetContentView (Resource.Layout.Main);

			//Get views
			var phoneNumberText = FindViewById<EditText> (Resource.Id.PhoneNumberText);
			var callButton = FindViewById<Button> (Resource.Id.CallButton);
			var callHistoryButton = FindViewById<Button> (Resource.Id.CallHistoryButton);


			var callHistory = new List<String> ();

			//Create click event for call button
			callButton.Click += delegate {

				//Get phone number from view
				var phoneNumber = phoneNumberText.Text;

				//Create alert
				var callDialog = new AlertDialog.Builder(this);

				callDialog.SetMessage("Call " + phoneNumber + "?");

				//Start Call Activity if clicked
				callDialog.SetNeutralButton("Call", delegate {

					//Add phone numbers to list
					callHistory.Add(phoneNumber);

					var callIntent = new Intent(Intent.ActionCall);

					callIntent.SetData(Android.Net.Uri.Parse("tel:" + phoneNumber));

					StartActivity(callIntent);

				});
				//Do nothing if cancelled
				callDialog.SetNegativeButton("Cancel",delegate {});

				callDialog.Show();
			};

			//Start Call History Activity and pass data through extras
			callHistoryButton.Click += delegate(object sender, EventArgs e) {
				var intent = new Intent(this,typeof(CallHistoryActivity));

				intent.PutStringArrayListExtra("callHistory",callHistory);

				StartActivity(intent);
			};

		}
	}
}


