package com.wb.baselib.utils;

import android.content.Context;
import android.graphics.MaskFilter;
import android.support.annotation.NonNull;
import android.text.Layout;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.AlignmentSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.MaskFilterSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.SubscriptSpan;
import android.text.style.SuperscriptSpan;
import android.text.style.TypefaceSpan;
import android.text.style.URLSpan;
import android.text.style.UnderlineSpan;
import android.widget.TextView;

/**
 * 类名: SpanUtil
 *
 * Description: 使TextView显示不同（颜色、大小）文字的工具类
 * 使用的关键类：
 * SpannableStringBuilder：使用setSpan(span, start, end, flag);来设置文字的Span格式
 * ForegroundColorSpan：前景色
 * BackgroundColorSpan：背景色
 * SubscriptSpan：下标
 * SuperscriptSpan：上标
 * UnderlineSpan：下划线
 * StrikethroughSpan：删除线
 * AbsoluteSizeSpan：绝对字体大小
 * RelativeSizeSpan：相对字体大小
 * URLSpan：Url
 * StyleSpan：加粗、倾斜、正常
 * TypefaceSpan：三种字体:"monospace", "serif", and "sans-serif"
 * MaskFilterSpan：BlurMaskFilter、EmbossMaskFilter
 *
 * Android Span 框架涉及的类遵循一下四个定义规则:(ctrl + H 查看层级结构（Android Studio），上面XxxSpan是这四个类或接口的部分子类或实现类)
 * 如果一个Span影响字符层次上的文字格式，那么它继承CharacterStyle类。
 * 如果一个Span影响段落层次上的文字格式，那么它实现ParagraphStyle接口。
 * 如果一个Span修改字符层次上的文字外观，那么它实现UpdateAppearance接口。
 * 如果一个Span修改字符层次上的度量或者尺寸，那么它实现UpdateLayout接口。
 * 虽然有以上四个规则，但是我们自定义Span时并不需要直接继承或者实现这些类或者接口，而是实现其他更加具体的类。
 *
 *
 * 知识点来自CSDN博客：http://blog.csdn.net/u012422440/article/details/52133037
 *
 * 这里作一个封装方便使用:前景色、背景色、下划线、删除线、上标、下标、相对字体大小、绝对字体大小等，移除或清空格式。
 * 使用如下：
 *
 SpanUtil.create()
 .addSection("这是") //添加一个字符串
 .addForeColorSection("红色", Color.RED) //添加前景色为红色的文字
 .addSection("字体，这是") //添加普通字符串
 .addForeColorSection("蓝色", Color.rgb(0x00, 0x00, 0xff)) //添加前景色为蓝色的文字
 .addSection("字体，这是")
 .addForeColorSection("绿色", 0xff00ff00) //添加前景色为绿色的文字
 .addSection("字体。")
 .setForeColor("这是", 0x90900090,false, SpanUtil.Which.ALL)//将所有"这是"渲染为紫色（0x90900090）
 .removeSpans("这是", 2) //移除下标2后第一个"这是"的Span样式
 .setForeColor("字体", Color.MAGENTA) //渲染最后一个"字体"的字体颜色为Color.MAGENTA
 //.clearSpans() //清除所有格式
 //.setAlign(Layout.Alignment.ALIGN_CENTER, 0, 1)//设置文字对齐方式
 //.addImage(R.mipmap.ic_launcher) //文字后添加图片
 .insertImage(this,R.mipmap.ic_launcher,3) //文字中插入图片
 .addStyleSection("加粗倾斜", Typeface.BOLD_ITALIC)
 .setForeColor("加粗倾斜",0xff6090f0)
 .showIn(mTvPackage); //显示到控件TextView中
 SpanUtil.create()
 .addSection("这是前景色") //添加普通字符串
 .setForeColor("前景色", Color.RED) //为"前景色"设置前景色
 .setForeColor(Color.BLUE,0,2) //为前两个字符设置前景色
 .addSection("，这是")
 .addBackColorSection("背景色",Color.MAGENTA) //添加带背景色的文字片段
 .addSection("，这是删除线")
 .setStrikethrough("删除线") //为文字片段"删除线"设置删除线
 .setForeColor("删除线",Color.LTGRAY) //设置文字颜色（前景色）
 .addSection("，市场价：")
 .addForeColorSection("￥29.99",Color.GRAY)  //添加带前景色的文字片段
 .setAbsSize("￥29.99",38) //设置绝对字体
 .setRelSize(".99",0.6f) //设置相对字体
 .setStrikethrough("￥29.99") //设置删除线
 .addSection("，本店：")
 .addForeColorSection("￥39.99",Color.RED)
 .setAbsSize("￥39.99",28)
 .setRelSize(".99",0.6f)
 .showIn(mTipView);
 SpanUtil.create().addSection("2")
 .addSuperSection("10") //添加上标
 .setRelSize("10",0.6f)
 .addSection(" = 1024，")
 .addSection("42 = 16")
 .setAsSuperscript("2") //设置为上标
 .setRelSize("2",0.6f)
 .showIn(mSectionView);
 *
 */

public final class SpanUtil {

    public static SpanBuilder create() {
        return new SpanBuilder();
    }

    public static class SpanBuilder {

        private SpannableStringBuilder spanStrBuilder;

        public SpanBuilder() {
            spanStrBuilder = new SpannableStringBuilder("");
        }

        /**
         * 添加文字片段
         */
        public SpanBuilder addSection(CharSequence section) {
            spanStrBuilder.append(section);
            return this;
        }

        /**
         * 添加字体片段
         *
         * @param section 要添加的文字
         * @param span
         * @param flag    可以是
         *                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE 不包含section前后文字，最常用
         *                Spanned.SPAN_INCLUSIVE_EXCLUSIVE 包含section前面文字
         *                Spanned.SPAN_EXCLUSIVE_INCLUSIVE 包含section后面文字
         *                Spanned.SPAN_INCLUSIVE_INCLUSIVE 包含section前后文字
         *                ……
         * @return SpanBuilder
         */
        public SpanBuilder addCertainSection(String section, Object span, int flag) {
            int start = spanStrBuilder.length();
            spanStrBuilder.append(section);
            int end = spanStrBuilder.length();
            spanStrBuilder.setSpan(span, start, end, flag);
            return this;
        }

        /**
         * 添加带前景色的文字片段
         */
        public SpanBuilder addForeColorSection(String section, int color) {
            return addCertainSection(section, new ForegroundColorSpan(color), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

        /**
         * 添加带背景色的文字片段
         */
        public SpanBuilder addBackColorSection(String section, int color) {
            return addCertainSection(section, new BackgroundColorSpan(color), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

        /**
         * 添加下标
         */
        public SpanBuilder addSubSection(String section) {
            return addCertainSection(section, new SubscriptSpan(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

        /**
         * 添加上标
         */
        public SpanBuilder addSuperSection(String section) {
            return addCertainSection(section, new SuperscriptSpan(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

        /**
         * 添加下划线片段
         */
        public SpanBuilder addUnderlineSection(String section) {
            return addCertainSection(section, new UnderlineSpan(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

        /**
         * 添加删除线片段
         */
        public SpanBuilder addStrickoutSection(String section) {
            return addCertainSection(section, new StrikethroughSpan(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

        /**
         * 添加绝对大小字体片段
         */
        public SpanBuilder addAbsSizeSection(String section, int size) {
            return addCertainSection(section, new AbsoluteSizeSpan(size), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

        /**
         * 添加相对大小字体片段
         */
        public SpanBuilder addRelSizeSection(String section, float proportion) {
            return addCertainSection(section, new RelativeSizeSpan(proportion), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

        /**
         * 添加url字体片段
         */
        public SpanBuilder addURLSection(String section, String url) {
            return addCertainSection(section, new URLSpan(url), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

        /**
         * 添加某种风格（Style）的文字片段
         *
         * @param section
         * @param style   TypeFace.BOLD  加粗
         *                TypeFace.BOLD_ITALIC  加粗倾斜
         *                TypeFace.ITALIC  倾斜
         *                TypeFace.NORMAL  正常
         * @return
         */
        public SpanBuilder addStyleSection(String section, int style) {
            return addCertainSection(section, new StyleSpan(style), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

        /**
         * 添加某种字体的文字片段
         *
         * @param section
         * @param family  The font family for this typeface.  Examples include
         *                "monospace", "serif", and "sans-serif".
         */
        public SpanBuilder addTypefaceSection(String section, String family) {
            return addCertainSection(section, new TypefaceSpan(family), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

        /**
         * 添加带Mask的文字片段
         *
         * @param maskFilter BlurMaskFilter、EmbossMaskFilter
         *                   例：//Blur a character
         *                   new BlurMaskFilter(density*2, BlurMaskFilter.Blur.NORMAL);
         *                   //Emboss a character
         *                   new EmbossMaskFilter(new float[] { 1, 1, 1 }, 0.4f, 6, 3.5f);
         * @return
         */
        public SpanBuilder addMaskSection(String section, MaskFilter maskFilter) {
            return addCertainSection(section, new MaskFilterSpan(maskFilter), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

        private SpanBuilder onDecor(String section, boolean ignoreCase, @NonNull Which which, DecorCallback decorCallback) {
            String baseStr = getString();
            if (ignoreCase) {
                section = section.toUpperCase();
                baseStr = baseStr.toUpperCase();
            }
            int start = 0;
            switch (which) {
                case FIRST:
                    start = baseStr.indexOf(section);
                    if (start == -1) break;
                    decorCallback.decor(start, start + section.length());
                    break;
                case LAST:
                    start = baseStr.lastIndexOf(section);
                    if (start == -1) break;
                    decorCallback.decor(start, start + section.length());
                    break;
                case ALL:
                    while (true) {
                        start = baseStr.indexOf(section, start);
                        if (start == -1) break;
                        decorCallback.decor(start, start + section.length());
                        start += section.length();
                    }
                    break;
            }
            return this;
        }


        private interface DecorCallback {
            void decor(int start, int end);
        }

        /**
         * 加上前景色
         *
         * @param ignoreCase boolean,true区分大小写；false,不区分大小写
         */
        public SpanBuilder setForeColor(final String section, final int color, boolean ignoreCase, @NonNull Which which) {

            return onDecor(section, ignoreCase, which, new DecorCallback() {
                @Override
                public void decor(int start, int end) {
                    setForeColor(color, start, end);
                }
            });
        }


        /**
         * 给最后该片段（section）加上前景色，不区分大小写
         */
        public SpanBuilder setForeColor(String section, int color) {
            return setForeColor(section, color, true, Which.LAST);
        }

        /**
         * 整体加上前景色
         */
        public SpanBuilder setForeColor(int color) {
            return setForeColor(color, 0, spanStrBuilder.length());
        }

        /**
         * 加上前景色
         */
        public SpanBuilder setForeColor(int color, int start, int end) {
            spanStrBuilder.setSpan(new ForegroundColorSpan(color), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            return this;
        }

        /**
         * 加上背景色
         */
        public SpanBuilder setBackColor(String section, final int color, boolean ignoreCase, @NonNull Which which) {

            return onDecor(section, ignoreCase, which, new DecorCallback() {
                @Override
                public void decor(int start, int end) {
                    setBackColor(color, start, end);
                }
            });
        }

        /**
         * 给最后一个该片段（section）加上背景色，不区分大小写
         */
        public SpanBuilder setBackColor(String section, int color) {
            return setBackColor(section, color, true, Which.LAST);
        }

        /**
         * 整体加上背景色
         */
        public SpanBuilder setBackColor(int color) {
            return setBackColor(color, 0, spanStrBuilder.length());
        }

        /**
         * 加上背景色
         */
        public SpanBuilder setBackColor(int color, int start, int end) {
            spanStrBuilder.setSpan(new BackgroundColorSpan(color), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            return this;
        }

        /**
         * 设置为下标
         */
        public SpanBuilder setAsSubscript(@NonNull String section, @NonNull Which which) {

            return onDecor(section, false, which, new DecorCallback() {
                @Override
                public void decor(int start, int end) {
                    setAsSubscript(start, end);
                }
            });
        }

        /**
         * 给最后一个该片段（section）设置为下标
         */
        public SpanBuilder setAsSubscript(String section) {
            return setAsSubscript(section, Which.LAST);
        }

        /**
         * 整体设置为下标
         */
        public SpanBuilder setAsSubscript() {
            return setAsSubscript(0, spanStrBuilder.length());
        }

        /**
         * 设置为下标
         */
        public SpanBuilder setAsSubscript(int start, int end) {
            spanStrBuilder.setSpan(new SubscriptSpan(), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            return this;
        }

        /**
         * 设置为上标
         */
        public SpanBuilder setAsSuperscript(String section, @NonNull Which which) {

            return onDecor(section, false, which, new DecorCallback() {
                @Override
                public void decor(int start, int end) {
                    setAsSuperscript(start, end);
                }
            });
        }

        /**
         * 给最后一个该片段（section）设置为上标
         */
        public SpanBuilder setAsSuperscript(String section) {
            return setAsSuperscript(section, Which.LAST);
        }

        /**
         * 整体设置为上标
         */
        public SpanBuilder setAsSuperscript() {
            return setAsSuperscript(0, spanStrBuilder.length());
        }

        /**
         * 设置为上标
         */
        public SpanBuilder setAsSuperscript(int start, int end) {
            spanStrBuilder.setSpan(new SuperscriptSpan(), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            return this;
        }

        /**
         * 给片段设置下划线
         */
        public SpanBuilder setUnderline(String section, @NonNull Which which) {

            return onDecor(section, false, which, new DecorCallback() {
                @Override
                public void decor(int start, int end) {
                    setUnderline(start, end);
                }
            });
        }

        /**
         * 给最后一个该片段（section）设置下划线
         */
        public SpanBuilder setUnderline(String section) {
            return setUnderline(section, Which.LAST);
        }

        /**
         * 给所有文字设置下划线
         */
        public SpanBuilder setUnderline() {
            return setUnderline(0, spanStrBuilder.length());
        }

        /**
         * 给片段设置下划线
         */
        public SpanBuilder setUnderline(int start, int end) {
            spanStrBuilder.setSpan(new UnderlineSpan(), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            return this;
        }

        /**
         * 给片段添加删除线
         */
        public SpanBuilder setStrikethrough(String section, @NonNull Which which) {

            return onDecor(section, false, which, new DecorCallback() {
                @Override
                public void decor(int start, int end) {
                    setStrikethrough(start, end);
                }
            });
        }

        /**
         * 给最后一个该片段（section）添加删除线
         */
        public SpanBuilder setStrikethrough(String section) {
            return setStrikethrough(section, Which.LAST);
        }

        /**
         * 给所有文字添加删除线
         */
        public SpanBuilder setStrikethrough() {
            return setStrikethrough(0, spanStrBuilder.length());
        }

        /**
         * 给片段添加删除线
         */
        public SpanBuilder setStrikethrough(int start, int end) {
            spanStrBuilder.setSpan(new StrikethroughSpan(), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            return this;
        }

        /**
         * 给片段的字体设置绝对大小
         */
        public SpanBuilder setAbsSize(String section, final int size, @NonNull Which which) {

            return onDecor(section, false, which, new DecorCallback() {
                @Override
                public void decor(int start, int end) {
                    setAbsSize(size, start, end);
                }
            });
        }

        /**
         * 给最后一个该片段（section）设置绝对大小
         */
        public SpanBuilder setAbsSize(String section, int size) {
            return setAbsSize(section, size, Which.LAST);
        }

        /**
         * 给所有文字设置绝对大小
         */
        public SpanBuilder setAbsSize(int size) {
            return setAbsSize(size, 0, spanStrBuilder.length());
        }

        /**
         * 给片段的字体设置绝对大小
         */
        public SpanBuilder setAbsSize(int size, int start, int end) {
            spanStrBuilder.setSpan(new AbsoluteSizeSpan(size), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            return this;
        }

        /**
         * 给片段的字体设置相对大小
         */
        public SpanBuilder setRelSize(String section, final float proportion, @NonNull Which which) {

            return onDecor(section, false, which, new DecorCallback() {
                @Override
                public void decor(int start, int end) {
                    setRelSize(proportion, start, end);
                }
            });
        }

        /**
         * 给最后一个该片段的字体设置相对大小
         */
        public SpanBuilder setRelSize(String section, float proportion) {
            return setRelSize(section, proportion, Which.LAST);
        }

        /**
         * 给片段的字体设置相对大小
         */
        public SpanBuilder setRelSize(float proportion) {
            return setRelSize(proportion, 0, spanStrBuilder.length());
        }

        /**
         * 给片段的字体设置相对大小
         */
        public SpanBuilder setRelSize(float proportion, int start, int end) {
            spanStrBuilder.setSpan(new RelativeSizeSpan(proportion), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            return this;
        }

        /**
         * 转为url字体片段
         */
        public SpanBuilder setAsURL(String section, final String url, @NonNull Which which) {

            return onDecor(section, false, which, new DecorCallback() {
                @Override
                public void decor(int start, int end) {
                    setAsUrl(url, start, end);
                }
            });
        }

        /**
         * 整体转为url字体片段
         */
        public SpanBuilder setAsUrl(String url) {
            return setAsUrl(url, 0, spanStrBuilder.length());
        }

        /**
         * 转为url字体片段
         */
        public SpanBuilder setAsUrl(String url, int start, int end) {
            spanStrBuilder.setSpan(new URLSpan(url), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            return this;
        }

        /**
         * 设置字体风格（粗、斜、正常）
         */
        public SpanBuilder setStyle(String section, final int style, @NonNull Which which) {

            return onDecor(section, false, which, new DecorCallback() {
                @Override
                public void decor(int start, int end) {
                    setStyle(style, start, end);
                }
            });
        }


        /**
         * 给最后一个片段（section）设置字体风格（粗、斜、正常）
         */
        public SpanBuilder setStyle(String section, int style) {
            return setStyle(section, style, Which.LAST);
        }

        /**
         * 整体设置字体风格（粗、斜、正常）
         */
        public SpanBuilder setStyle(int style) {
            return setStyle(style, 0, spanStrBuilder.length());
        }

        /**
         * 设置字体风格（粗、斜、正常）
         */
        public SpanBuilder setStyle(int style, int start, int end) {
            spanStrBuilder.setSpan(new StyleSpan(style), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            return this;
        }

        /**
         * 设置字体
         *
         * @param family "monospace", "serif", and "sans-serif"
         * @param which  SpanUtil.Which.FIRST、SpanUtil.Which.LAST、SpanUtil.Which.ALL
         */
        public SpanBuilder setTypeface(String section, final String family, @NonNull Which which) {

            return onDecor(section, false, which, new DecorCallback() {
                @Override
                public void decor(int start, int end) {
                    setTypeface(family, start, end);
                }
            });
        }

        /**
         * 给最后一个该片段（section）设置字体
         */
        public SpanBuilder setTypeface(String section, String family) {
            return setTypeface(section, family, Which.LAST);
        }

        /**
         * 整体设置字体
         */
        public SpanBuilder setTypeface(String family) {
            return setTypeface(family, 0, spanStrBuilder.length());
        }

        /**
         * 设置字体
         */
        public SpanBuilder setTypeface(String family, int start, int end) {
            spanStrBuilder.setSpan(new TypefaceSpan(family), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            return this;
        }

        /**
         * 设置Mask
         *
         * @param maskFilter BlurMaskFilter、EmbossMaskFilter
         *                   例：//Blur((使…)变模糊) a character
         *                   new BlurMaskFilter(density*2, BlurMaskFilter.Blur.NORMAL);
         *                   //Emboss a character
         *                   new EmbossMaskFilter(new float[] { 1, 1, 1 }, 0.4f, 6, 3.5f);
         */
        public SpanBuilder setMask(String section, final MaskFilter maskFilter, @NonNull Which which) {

            return onDecor(section, false, which, new DecorCallback() {
                @Override
                public void decor(int start, int end) {
                    setMask(maskFilter, start, end);
                }
            });
        }

        /**
         * 为最后一个该片段（section）设置Mask
         *
         * @param maskFilter BlurMaskFilter、EmbossMaskFilter
         *                   例：//Blur a character
         *                   new BlurMaskFilter(density*2, BlurMaskFilter.Blur.NORMAL);
         *                   //Emboss a character
         *                   new EmbossMaskFilter(new float[] { 1, 1, 1 }, 0.4f, 6, 3.5f);
         */
        public SpanBuilder setMask(String section, MaskFilter maskFilter) {
            return setMask(section, maskFilter, Which.LAST);
        }

        /**
         * 为整体设置Mask
         *
         * @param maskFilter BlurMaskFilter、EmbossMaskFilter
         *                   例：//Blur a character
         *                   new BlurMaskFilter(density*2, BlurMaskFilter.Blur.NORMAL);
         *                   //Emboss a character
         *                   new EmbossMaskFilter(new float[] { 1, 1, 1 }, 0.4f, 6, 3.5f);
         */
        public SpanBuilder setMask(MaskFilter maskFilter) {
            return setMask(maskFilter, 0, spanStrBuilder.length());
        }

        /**
         * 设置Mask
         *
         * @param maskFilter BlurMaskFilter、EmbossMaskFilter
         *                   例：//Blur a character
         *                   new BlurMaskFilter(density*2, BlurMaskFilter.Blur.NORMAL);
         *                   //Emboss a character
         *                   new EmbossMaskFilter(new float[] { 1, 1, 1 }, 0.4f, 6, 3.5f);
         * @param start      开始位置
         * @param end        截止位置
         */
        public SpanBuilder setMask(MaskFilter maskFilter, int start, int end) {
            spanStrBuilder.setSpan(new MaskFilterSpan(maskFilter), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            return this;
        }

        /**
         * 设置对齐方式
         *
         * @param alignment Layout.Alignment.ALIGN_CENTER 居中
         *                  Layout.Alignment.ALIGN_NORMAL 正常（左对齐）
         *                  Layout.Alignment.ALIGN_OPPOSITE 反向（右对齐）
         * @param start     开始位置
         * @param end       截止位置
         */
        public SpanBuilder setAlign(Layout.Alignment alignment, int start, int end) {
            spanStrBuilder.setSpan(new AlignmentSpan.Standard(alignment), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            return this;
        }

        /**
         * 文字后添加图片
         *
         * @param resId
         * @return
         */
        public SpanBuilder addImage(Context context, int resId) {
            insertImage(context, resId, spanStrBuilder.length());
            return this;
        }

        /**
         * 文字中某位置（where）插入图片
         *
         * @param resId 图片资源Id
         * @param where 插入位置：占一个字的位置，整体索引增加一个
         * @return
         */
        public SpanBuilder insertImage(Context context, int resId, int where) {
            spanStrBuilder.insert(where, " ");
            spanStrBuilder.setSpan(new ImageSpan(context, resId), where, where + 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            return this;
        }

        /**
         * 清除格式
         */
        public SpanBuilder clearSpans() {
            spanStrBuilder.clearSpans();
            return this;
        }

        /**
         * 移除格式
         */
        public SpanBuilder removeSpans(@NonNull String section, @NonNull Which which) {

            return onDecor(section, false, which, new DecorCallback() {
                @Override
                public void decor(int start, int end) {
                    removeSpans(start, end);
                }
            });
        }

        /**
         * 移除最后一个该片段（section）的格式
         */
        public SpanBuilder removeSpans(@NonNull String section) {
            return removeSpans(section, Which.LAST);
        }

        /**
         * 移除格式，从某一个下标开始
         */
        public SpanBuilder removeSpans(@NonNull String section, int fromIndex) {
            String baseStr = getString();
            fromIndex = baseStr.indexOf(section, fromIndex);
            removeSpans(fromIndex, fromIndex + section.length());
            return this;
        }

        /**
         * 移除格式
         *
         * @param start 开始位置
         * @param end   结束位置
         * @return this
         */
        public SpanBuilder removeSpans(int start, int end) {
            Object[] spans = spanStrBuilder.getSpans(start, end, Object.class);
            for (Object span : spans) {
                spanStrBuilder.removeSpan(span);
            }
            return this;
        }

        /**
         * 获得当前SpanStringBuilder实例
         */
        public SpannableStringBuilder getSpanStrBuilder() {
            return spanStrBuilder;
        }

        /**
         * 获得当前SpanStringBuilder中的字符串
         */
        public String getString() {
            return spanStrBuilder.toString();
        }

        /**
         * 显示到控件
         *
         * @param textView
         */
        public void showIn(TextView textView) {
            textView.setText(spanStrBuilder);
            spanStrBuilder.clearSpans();
            spanStrBuilder.clear();
            spanStrBuilder = null;
        }
    }

    /**
     * 标记第一个、最后一个、所有
     */
    public enum Which {
        FIRST, LAST, ALL
    }

}
