# MonkeySimulator
Tool encapsulating monkey command for testing Android apps

Current version is only used for BlackBerry apps.

Version 1.0

1. Fetch all BlackBerry apps first(Both apps and services), displaying as app name with current version.
2. Select one from app list and entry Monkey parameteres.
3. Click Start to execute Monkey command.
4. Result displays in report page.
5. Go back to app list or save the report to SD card for further analysis.


Version 1.1

1. Display the entry value at para list after press OK.
2. Reconstructure part of methods to decrease coupling.
  
Version 1.2

1. Add Category para.
2. Separate all Strings inside the code.
3. Be able to save input values for next time convenience.

Bug:

    Input empty value on optional paras works fine on DTEK60 but doesn't on KeyOne. 

Version 2.0

1. Add button control.
