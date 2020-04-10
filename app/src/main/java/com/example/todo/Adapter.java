package com.example.todo;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder>{
    private CategoryDao categoryDao = MyApplication.getInstance().getDatabase().categoryDao();
    List<Todo> todoList;
    private TodoItemClickListener listener;
    //инициализируем переменные с помощью конструктура
    public Adapter(List<Todo> todoList, @Nullable TodoItemClickListener listener) {
        this.todoList = todoList;
        this.listener = listener;
    }


    // Создаем новые вюшки
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        //from database
        final Todo todo = todoList.get(position);
        holder.itemTitle.setText(todo.title);
        holder.itemStatus.setText(todo.status);
        String category = "category";
        //try get catbyid
        try {
            category = categoryDao.getCategoryById(todo.categoryId).title;
        }
        catch (Exception e) {
            Log.e("Error: ", e.toString());
        }

        holder.itemCategory.setText(category);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            //Чтобы показать детальную информацию
            @Override
            public void onClick(View v) {
                listener.itemClick(position, todo);
            }
        });
    }
    //Возвращаем размер данных
    @Override
    public int getItemCount() {
        return todoList.size();
    }

    // Предоставляем ссылку на представления для каждого элемента данных
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView itemTitle;
        TextView itemCategory;
        TextView itemStatus;
        public ViewHolder(View itemview){
            super(itemview);
            itemTitle=itemview.findViewById(R.id.title);
            itemCategory=itemview.findViewById(R.id.category);
            itemStatus=itemview.findViewById(R.id.status);

        }
    }
    //слушатель
    public interface TodoItemClickListener {
        void itemClick(int position, Todo todo);
    }
}