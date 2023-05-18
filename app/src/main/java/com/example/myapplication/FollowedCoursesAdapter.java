package com.example.myapplication;

public class FollowedCoursesAdapter extends RecyclerView.Adapter<FollowedCoursesAdapter.ViewHolder> {
    private List<Data> followedCourses;

    public FollowedCoursesAdapter(List<Data> followedCourses) {
        this.followedCourses = followedCourses;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_followed_course, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Data course = followedCourses.get(position);
        holder.textCourseName.setText(course.getCourseName());
        holder.textCourseUpdate.setText(course.getUpdateMessage());
    }

    @Override
    public int getItemCount() {
        return followedCourses.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textCourseName;
        TextView textCourseUpdate;

        public ViewHolder(View itemView) {
            super(itemView);
            textCourseName = itemView.findViewById(R.id.text_course_name);
            textCourseUpdate = itemView.findViewById(R.id.text_course_update);
        }
    }
}
