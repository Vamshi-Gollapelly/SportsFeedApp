package com.vamshigollapelly.sportsfeedapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.vamshigollapelly.sportsfeedapp.R;
import com.vamshigollapelly.sportsfeedapp.adapters.FeaturedAdapter;
import com.vamshigollapelly.sportsfeedapp.adapters.NewsAdapter;
import com.vamshigollapelly.sportsfeedapp.data.NewsItem;
import com.vamshigollapelly.sportsfeedapp.data.NewsRepository;
import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private NewsAdapter newsAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView rvFeatured = view.findViewById(R.id.rvFeatured);
        RecyclerView rvNews = view.findViewById(R.id.rvNews);
        SearchView searchView = view.findViewById(R.id.searchView);

        // Featured - horizontal
        rvFeatured.setLayoutManager(
                new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        rvFeatured.setAdapter(new FeaturedAdapter(
                NewsRepository.getFeaturedMatches(),
                item -> navigateToDetail(view, item.id)
        ));

        // News - 2 column grid to match wireframe
        newsAdapter = new NewsAdapter(
                NewsRepository.getAllNews(),
                item -> navigateToDetail(view, item.id)
        );
        rvNews.setLayoutManager(new GridLayoutManager(getContext(), 2));
        rvNews.setAdapter(newsAdapter);

        // Search bar filter
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                filterNews(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterNews(newText);
                return true;
            }
        });
    }

    private void filterNews(String query) {
        List<NewsItem> filtered = new ArrayList<>();
        for (NewsItem item : NewsRepository.getAllNews()) {
            if (query.isEmpty() ||
                    item.category.toLowerCase().contains(query.toLowerCase()) ||
                    item.title.toLowerCase().contains(query.toLowerCase())) {
                filtered.add(item);
            }
        }
        newsAdapter.updateList(filtered);
    }

    private void navigateToDetail(View view, int newsId) {
        Bundle bundle = new Bundle();
        bundle.putInt("newsId", newsId);
        Navigation.findNavController(view).navigate(R.id.action_home_to_detail, bundle);
    }
}