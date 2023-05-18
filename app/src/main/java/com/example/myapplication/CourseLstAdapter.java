public class CourseLstAdapter extends ArrayAdapter<Data> {
    private Context mContext;
    int mResource;
    private List<Data> followedCourses;

    public CourseLstAdapter(@NonNull Context context, int resource, @NonNull List<Data> objects, List<Data> followedCourses) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
        this.followedCourses = followedCourses;
    }

    @SuppressLint("ViewHolder")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String courseId = getItem(position).getCourseId();
        String courseName = getItem(position).getCourseName();

        // Inflate the layout for the item
        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        // Set the course name
        TextView tvCourseName = convertView.findViewById(R.id.course_name);
        tvCourseName.setText(courseName);

        // Find the follow button/icon in the layout
        Button followButton = convertView.findViewById(R.id.follow_button);

        // Set click listener for the follow button/icon
        followButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Check if the course is already followed
                if (followedCourses.contains(getItem(position))) {
                    // Course is already followed, show toast message or perform desired action
                    Toast.makeText(mContext, "Course already followed", Toast.LENGTH_SHORT).show();
                } else {
                    // Course is not followed, add it to the followed courses list
                    followedCourses.add(getItem(position));

                    // Update the adapter to reflect the changes
                    notifyDataSetChanged();

                    // Show toast message or perform desired action
                    Toast.makeText(mContext, "Course followed: " + courseName, Toast.LENGTH_SHORT).show();
                }
            }
        });

        return convertView;
    }
}
