# Earthquake Radar

"Earthquake Radar" is an innovative mobile application developed during my time at Robert Gordon University, designed to provide users with real-time updates and visualization of seismic activities across geographical regions. Leveraging cutting-edge technology and data from seismic monitoring stations worldwide, the app delivers crucial information about recent earthquakes, including their magnitude, location, depth, and time of occurrence.

<img src = "https://github.com/Oaky112/Earthquake-Radar/assets/88333204/136e6aec-afc1-4cdb-a62b-34c23601ee9c" width="150" height="300">
<img src = "https://github.com/Oaky112/Earthquake-Radar/assets/88333204/1e367d4c-f806-4ca9-8131-45a179a3a363" width="150" height="300">
<img src = "https://github.com/Oaky112/Earthquake-Radar/assets/88333204/eec1833d-161c-49ff-924f-9301d1af2107" width="150" height="300">
<img src = "https://github.com/Oaky112/Earthquake-Radar/assets/88333204/9cdaaa9b-d86d-47df-910c-de10d451b5bb" width="150" height="300">

###  Built in Java using Android Studio and utilizing native Android features


## 1. App Plan 
Ideated and developed by: Oaky112
<br>
Title and promo: EarthquakeTracker - Stay Informed About Seismic Activity!

Description: The app allows users to track seismic activity by providing real-time updates and visualizations of earthquakes. Find a list of included features below:

- Three interlinked pages (including navigation without loading and splash pages)
- Utilization of various widgets to obtain inputs from users:
  - Customized EditText fields to input earthquake details such as magnitude, location, and depth.
  - DatePickerDialog to select the occurrence date of the earthquake.
  - Map interface allowing users to explore earthquake-prone regions and interact with earthquake markers.
- Integration of camera intent to capture images related to seismic events.
  - Utilization of various widgets to display information to the user, including customized labels and components.
  - Implementation of beautiful animations to provide user feedback during interactions.
  - User feedback and validation of input fields to ensure data accuracy.
- Implementation of a RecyclerView to display a list of recent earthquakes sorted by occurrence date.
- Utilization of different layout files to structure app components effectively.
- Accessible navigation through the app pages for seamless user experience.
- Integration of ROOM SQLite data store for offline-first data storage approach.
- Utilization of services like Camera and Notification for enhanced functionality.

## 2. App Design
I generated the app icon with the use of DALL·E 2, and further edited it to be more in theme to my app with external tools

## App Overview:
Navigation Graph:
<br>
<img src = "https://github.com/Oaky112/Earthquake-Radar/assets/88333204/fb988e7b-28af-47bb-8970-e220d35bccfb" width="400" height="400">


## App Structure:
- Landing Page:
  -  The Landing Page serves as the initial screen users encounter upon opening the app. It provides a simple and friendly explanation of the app's purpose, accompanied by a prominent action button that directs users to the core view of the app: the Map Page. All methods related to this page are implemented within the onCreateView method for easier view management. Implemented with a LinearLayout.

 - Map Page:
   - The Map Page offers users a real-time interactive map interface for visualizing seismic activity worldwide. Users can explore earthquake-prone regions, interact with earthquake markers, and access detailed information about each event.

- Dashboard Page:
  - The Dashboard Page serves as a centralized hub for users to input data and gather comprehensive information about earthquakes they have experienced or witnessed. It allows users to input earthquake details, view a list of recent earthquakes, and access additional features.


- Options (Add an Earthquake):
  - The Options fragment allows users to input earthquake data, including magnitude, location, and occurrence date. It features a camera intent for users to capture images related to seismic events. All earthquake information is stored in a Room local database through the EarthquakeRepository class. Implemented with a LinearLayout.

- Earthquake Info:
  - The Earthquake Info page displays detailed information about each earthquake, including magnitude, location, and depth. Users can also view images captured with the camera intent, delete earthquakes, or navigate back to the schedule. Implemented with a LinearLayout.

- Earthquake Added/Removed Views:
  - These fragments provide animations for user feedback during earthquake addition or removal operations. They ensure users are aware that the app is processing their requests. These views do not appear on the navigation image.


## 3. Key Features
<b><u>Real-time Earthquake Monitoring:</u></b> Continuously monitors seismic activities globally, fetching data from reliable sources such as the United States Geological Survey (USGS) and other reputable seismic monitoring networks.

<b>Interactive Map Interface:</b> Employs an interactive map interface powered by the Google Maps Android API, allowing users to explore earthquake-prone regions, zoom in for detailed views, and interact with individual earthquake markers for comprehensive information.

<b>Dynamic Marker Placement:</b> Dynamically adds pinpoint markers to the map upon detecting a new earthquake event, accurately indicating the location of the seismic activity. Each marker is color-coded to represent the earthquake's magnitude.

<b>Detailed Earthquake Information:</b> Provides detailed information about each earthquake, including its magnitude on the Richter scale, exact coordinates, depth below the Earth's surface, and precise time of occurrence.

<b>Customizable Alerts:</b> Offers customizable alert settings, enabling users to receive notifications based on their preferred criteria, such as earthquake magnitude thresholds or proximity to their current location.

## 4. Technical Implementation
<b>Google Maps Integration:</b> Seamlessly integrates with the Google Maps Android API to provide an interactive mapping experience.

<b>Data Retrieval from Seismic Networks:</b> Fetches real-time earthquake data from authoritative sources using HTTP requests and APIs.

<b>Dynamic Marker Generation:</b> Programmatically generates marker objects using the Google Maps API, placing them at the corresponding geographic coordinates on the map.

<b>User Alerts and Notifications:</b> Employs background services or asynchronous tasks to periodically check for new earthquake events and trigger notifications to alert the user.

## 5. Color Scheme Selection
### <b>The color scheme for Earthquake Radar was chosen deliberately to enhance user experience and convey information effectively. Dark blue was selected as the primary color for several reasons:</b>

Visual Contrast: Dark blue provides a strong visual contrast against the map background, ensuring that earthquake markers and other interface elements stand out prominently for easy identification.

Associations with Water: Dark blue is often associated with water, which is relevant to earthquakes occurring primarily near tectonic plate boundaries, including oceanic regions.

Calm and Trustworthy: Dark blue conveys a sense of calmness and trustworthiness, which is important for an application providing critical information about natural disasters. It instills confidence in users regarding the reliability of the data and the app's functionality.

Accessibility: Dark blue is a color that is generally accessible to users with different visual abilities, ensuring inclusivity and usability for a wide range of individuals.


## 6. Screenshots
<img src = "https://github.com/Oaky112/Earthquake-Radar/assets/88333204/136e6aec-afc1-4cdb-a62b-34c23601ee9c" width="400" height="800">
<img src = "https://github.com/Oaky112/Earthquake-Radar/assets/88333204/1e367d4c-f806-4ca9-8131-45a179a3a363" width="400" height="800">
<img src = "https://github.com/Oaky112/Earthquake-Radar/assets/88333204/eec1833d-161c-49ff-924f-9301d1af2107" width="400" height="800">
<img src = "https://github.com/Oaky112/Earthquake-Radar/assets/88333204/9cdaaa9b-d86d-47df-910c-de10d451b5bb" width="400" height="800">

## 7. Conclusion

Earthquake Radar offers a comprehensive solution for earthquake monitoring and awareness. By combining advanced mapping capabilities with real-time earthquake data, the app empowers users to stay informed and take proactive measures to mitigate earthquake risks. With its intuitive interface, customizable alerts, and educational resources, Earthquake Radar serves as a valuable tool for individuals, communities, and researchers alike.

