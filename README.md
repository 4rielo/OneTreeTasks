This is a Technical Challenge app, in Kotlin multiplatform.

It displays a Tasks list, with a button to create a new task.

When creating a new task, in either iOS or Android, the location permission will be requested to register where was the task created.

Clicking on a task opens up the Task detail screen.

Task can be marked as completed via the checkbox button, or swiping right on the task list screen.

Task can be removed either by clicking on the trash can icon, in the Task Detail screen, or by swiping right on the Task List screen.


To run either iOS or Android app, just select the correspondant "Build Configuration", and click the play button.
To run the Desktop App, select the "MainKt" build configuration, and in the console type "./gradlew run".