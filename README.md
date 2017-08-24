# MonkeySimulator
Tool encapsulating monkey command for performance and pressure testing on Android apps

Current version is only used for **BlackBerry** apps.

For future update, please check my personal Github:
[Github for Monkey App](https://github.com/Ruins7/MonkeySimulator)

Monkey parameters range:
event number : 1-20000 (required)
seed number : 0(empty)-2000
information level : 0(empty)-5
throttle : 0(empty)-1000

pct-touch : 0(empty)-100(%)
pct-motion : 0(empty)-100(%)
pct-trackball : 0(empty)-100(%)
pct-nav : 0(empty)-100(%)
pct-majornav : 0(empty)-100(%)
pct-syskeys : 0(empty)-100(%)
pct-appswitch : 0(empty)-100(%)
pct-anyevent : 0(empty)-100(%)

dbg-no-event : empty or 1
hprof : empty or 1
ignore-timeouts : empty or 1
ignore-crashes : empty or 1
ignore-security-exceptions : empty or 1
kill-process-after-error : empty or 1
monitor-native-crashes : empty or 1
wait-dbg : empty or 1

For parameters detail, please check [Monkey offical document](https://developer.android.com/studio/test/monkey.html)

**To install Monkey, the device has to be rooted**

If there is system permission issue during installation, try to sign this app with 
[signapk.jar](https://drive.google.com/file/d/0B2nrgXGUhkR8UDhIM2NjaEtNWnc/view?usp=sharing), [platform.x509.pem](https://drive.google.com/file/d/0B2nrgXGUhkR8T0VuVW1kUXluZjg/view?usp=sharing) and [platform.pk8](https://drive.google.com/file/d/0B2nrgXGUhkR8bHVBWjZRVjZYNTg/view?usp=sharing)

**Version 1.0**

1. Fetch all BlackBerry apps first(Both apps and services), displaying as app name with current version.
2. Select one from app list and entry Monkey parameteres.
3. Click Start to execute Monkey command.
4. Result displays in report page.
5. Go back to app list or save the report to SD card for further analysis.


**Version 1.1**

1. Display the entry value at para list after press OK.
2. Reconstructure part of methods to decrease coupling.
  
**Version 1.3**


1. Separate all Strings inside the code.
2. Be able to save input values for next time convenience.

**Bug:**
    
    1. Inputing empty value on optional paras works fine on KeyOne but doesn't on DTEK60. 
    
**Version 2.0**

1. Add Category para.

**Version 2.1**

1. Add filter to the final result display. eg. Exceptions, errors, crashes.
2. Save execution result while monkey running to SD card(in case of crash).
3. pre-load recommended parameters value.

**Version 2.2**

1. Add button to control Monkey during execution.
2. Control Monkey to go deeper in a specific path in one app. 
3. Improve result issue filter to get more valuable info so that
  it’s easy to locate issue efficiently.