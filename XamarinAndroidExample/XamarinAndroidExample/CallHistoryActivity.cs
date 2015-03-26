
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

using Android.App;
using Android.Content;
using Android.OS;
using Android.Runtime;
using Android.Views;
using Android.Widget;

namespace XamarinAndroidExample
{
	//Make sure to set activity to listActivity!!
	[Activity (Label = "Call History")]			
	public class CallHistoryActivity : ListActivity
	{
		protected override void OnCreate (Bundle bundle)
		{
			base.OnCreate (bundle);

			//Special C# syntax :)
			var callHistory = this.Intent.Extras.GetStringArrayList ("callHistory") ?? new List<String> ();

			//set listView using listAdapter
			this.ListAdapter = new ArrayAdapter<String> (this, Android.Resource.Layout.SimpleListItem1, callHistory);

		}
	}
}

