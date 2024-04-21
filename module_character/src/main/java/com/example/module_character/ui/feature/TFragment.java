package com.example.module_character.ui.feature;

import static android.app.Activity.RESULT_OK;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.example.common.base.BaseFragment;
import com.example.common.util.Utils;
import com.example.module_character.R;
import com.example.module_character.databinding.FragmentTBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.transition.MaterialFade;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.text.Text;
import com.google.mlkit.vision.text.TextRecognition;
import com.google.mlkit.vision.text.TextRecognizer;
import com.google.mlkit.vision.text.chinese.ChineseTextRecognizerOptions;

import java.io.IOException;

/**
 * @Author winiymissl
 * @Date 2024-04-21 16:46
 * @Version 1.0
 */
public class TFragment extends BaseFragment<FragmentTBinding> {
    private NavController navController;
    static final int REQUEST_PIC_CODE = 2;
    static final int REQUEST_TAKEPHOTO_CODE = 1;
    private static final int CAMERA_REQUEST = 3;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setEnterTransition(new MaterialFade());
        setExitTransition(new MaterialFade());
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = NavHostFragment.findNavController(this);
        binding.materialButtonPic.setOnClickListener(v -> {
            /*
             * 选择照片
             * */
            // 创建一个 Intent，指定动作为 ACTION_PICK，数据为图片类型
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

            // 设置类型为图片类型
            intent.setType("image/*");

            // 开启相册应用
            startActivityForResult(intent, REQUEST_PIC_CODE);
        });
        binding.materialButtonTakePhoto.setOnClickListener(v -> {
            /*
             * 拍照片
             * */
            // 检查相机权限
            if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                // 请求相机权限
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA}, CAMERA_REQUEST);
            } else {
                dispatchTakePictureIntent();
            }
        });
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        TextRecognizer recognizer = TextRecognition.getClient(new ChineseTextRecognizerOptions.Builder().build());
        if (requestCode == REQUEST_TAKEPHOTO_CODE) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(getActivity(), "结果良好", Toast.LENGTH_SHORT).show();
                Bundle extras = data.getExtras();
                Bitmap bitmap = (Bitmap) extras.get("data");
                InputImage image = InputImage.fromBitmap(bitmap, 0);

                Task<Text> result = recognizer.process(image).addOnSuccessListener(new OnSuccessListener<Text>() {
                    @Override
                    public void onSuccess(Text visionText) {
                        String resultText = visionText.getText();
                        Bundle b = new Bundle();
                        b.putString("text", resultText);
                        navController.navigate(R.id.textDialogFragment, b);
                        Log.d("世界是一个bug", resultText.toString());
                        for (Text.TextBlock block : visionText.getTextBlocks()) {
                            String blockText = block.getText();
                            Point[] blockCornerPoints = block.getCornerPoints();
                            Rect blockFrame = block.getBoundingBox();
                            for (Text.Line line : block.getLines()) {
                                String lineText = line.getText();
                                Point[] lineCornerPoints = line.getCornerPoints();
                                Rect lineFrame = line.getBoundingBox();
                                for (Text.Element element : line.getElements()) {
                                    String elementText = element.getText();
                                    Point[] elementCornerPoints = element.getCornerPoints();
                                    Rect elementFrame = element.getBoundingBox();
                                    for (Text.Symbol symbol : element.getSymbols()) {
                                        String symbolText = symbol.getText();
                                        Point[] symbolCornerPoints = symbol.getCornerPoints();
                                        Rect symbolFrame = symbol.getBoundingBox();
                                    }
                                }
                            }
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Utils.tryAgain(e.getMessage(), getActivity());
                    }
                });
            }
        }
        if (requestCode == REQUEST_PIC_CODE) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(getActivity(), "结果良好", Toast.LENGTH_SHORT).show();
                InputImage image = null;
                Uri uri = data.getData();
                try {
                    image = InputImage.fromFilePath(getActivity(), uri);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Task<Text> result = recognizer.process(image).addOnSuccessListener(new OnSuccessListener<Text>() {
                    @Override
                    public void onSuccess(Text visionText) {
                        String resultText = visionText.getText();
                        Log.d("世界是一个bug", resultText.toString());
                        Bundle bundle = new Bundle();
                        bundle.putString("text", resultText);
                        navController.navigate(R.id.textDialogFragment, bundle);

                        for (Text.TextBlock block : visionText.getTextBlocks()) {
                            String blockText = block.getText();
                            Point[] blockCornerPoints = block.getCornerPoints();
                            Rect blockFrame = block.getBoundingBox();
                            for (Text.Line line : block.getLines()) {
                                String lineText = line.getText();
                                Point[] lineCornerPoints = line.getCornerPoints();
                                Rect lineFrame = line.getBoundingBox();
                                for (Text.Element element : line.getElements()) {
                                    String elementText = element.getText();
                                    Point[] elementCornerPoints = element.getCornerPoints();
                                    Rect elementFrame = element.getBoundingBox();
                                    for (Text.Symbol symbol : element.getSymbols()) {
                                        String symbolText = symbol.getText();
                                        Point[] symbolCornerPoints = symbol.getCornerPoints();
                                        Rect symbolFrame = symbol.getBoundingBox();
                                    }
                                }
                            }
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Utils.tryAgain(e.getMessage(), getActivity());
                    }
                });
            }
        }
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_TAKEPHOTO_CODE);
        } else {
            Toast.makeText(getActivity(), "没有找到相机应用", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_TAKEPHOTO_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // 用户授予了相机权限，打开相机
                dispatchTakePictureIntent();
                Toast.makeText(getActivity(), "相机权限允许", Toast.LENGTH_SHORT).show();
            } else {
                // 用户拒绝了相机权限，提示用户并关闭活动
                Toast.makeText(getActivity(), "相机权限被拒绝", Toast.LENGTH_SHORT).show();
                getActivity().finish();
            }
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_t, container, false);
        binding = FragmentTBinding.bind(view);
        return binding.getRoot();
    }
}
