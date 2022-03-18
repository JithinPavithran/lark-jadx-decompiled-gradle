package com.ss.android.lark.chatsetting.impl.group.setting;

import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.larksuite.component.ui.button.LKUISwitchButton;
import com.larksuite.component.ui.imageview.LKUIRoundedImageView2;
import com.larksuite.suite.R;
import com.ss.android.lark.ui.CommonTitleBar;
import com.ss.android.lark.widget.chat_function_view.ChatFunctionView;

public class GroupSettingView_ViewBinding implements Unbinder {

    /* renamed from: a */
    private GroupSettingView f90192a;

    @Override // butterknife.Unbinder
    public void unbind() {
        GroupSettingView groupSettingView = this.f90192a;
        if (groupSettingView != null) {
            this.f90192a = null;
            groupSettingView.mScrollView = null;
            groupSettingView.mTitleBar = null;
            groupSettingView.mGroupMemberGv = null;
            groupSettingView.mGroupMemberLayout = null;
            groupSettingView.mGroupMemberTitleTv = null;
            groupSettingView.mChatterAllCountTV = null;
            groupSettingView.mGroupSettingLayout = null;
            groupSettingView.mGroupSettingTv = null;
            groupSettingView.mStartPositionLayout = null;
            groupSettingView.mStartPositionTV = null;
            groupSettingView.mChatSettingTitlt = null;
            groupSettingView.mMuteLayoutWrapper = null;
            groupSettingView.mMuteLayoutBottomDivider = null;
            groupSettingView.mMsgNotifyTV = null;
            groupSettingView.mSwitchButton = null;
            groupSettingView.mMsgNotifySubtitle = null;
            groupSettingView.mAutoTranslateLayout = null;
            groupSettingView.mAutoTranslateTipsTv = null;
            groupSettingView.mAutoTranslateSwitch = null;
            groupSettingView.mAutoTranslateLabelTv = null;
            groupSettingView.mNicknameLayout = null;
            groupSettingView.mNickTipTv = null;
            groupSettingView.mNicknameTv = null;
            groupSettingView.mRedDot = null;
            groupSettingView.mAddQuickSwitchLayout = null;
            groupSettingView.mAddQuickSwitchTitle = null;
            groupSettingView.mAddQuickSwitchSubtitle = null;
            groupSettingView.mShortcutSwitchButton = null;
            groupSettingView.mChatBoxLayout = null;
            groupSettingView.mMoveToBoxSwitchButton = null;
            groupSettingView.mMoveToBoxTitle = null;
            groupSettingView.mMoveToBoxSubtitle = null;
            groupSettingView.mThreadFavoriteLayout = null;
            groupSettingView.mThreadSubscribeSwitch = null;
            groupSettingView.mTvSubscribeThreadTitle = null;
            groupSettingView.mTvSubscribeThreadSubTitle = null;
            groupSettingView.mIvSubscribeThreadDesc = null;
            groupSettingView.mChatFunctionView = null;
            groupSettingView.mOnCallRegionNew = null;
            groupSettingView.mOnCallDescTvNew = null;
            groupSettingView.mOnCallDescMoreArrowNew = null;
            groupSettingView.mOnCallAboutTvNew = null;
            groupSettingView.mLeaveGroupTV = null;
            groupSettingView.mReportBtn = null;
            groupSettingView.mSecretChatTransferTo = null;
            groupSettingView.mDissolveGroupBtn = null;
            groupSettingView.mSearchChatLayout = null;
            groupSettingView.mSearchChatTypeRv = null;
            groupSettingView.mSearchChatTitleLayout = null;
            groupSettingView.mChatFunctionLayout = null;
            groupSettingView.mChatInfoRedesignView = null;
            groupSettingView.mGroupAvatarRedesign = null;
            groupSettingView.mGroupNameTvRedesign = null;
            groupSettingView.mGroupNameIconView = null;
            groupSettingView.mTeamNameTv = null;
            groupSettingView.mGroupQrcodeIvRedesign = null;
            groupSettingView.mGroupInfoArrow = null;
            groupSettingView.mGroupAvatarSecretIv = null;
            groupSettingView.mGroupRegionDivider = null;
            groupSettingView.mOnCallHeaderIv = null;
            groupSettingView.mOnCallGroupNameTv = null;
            groupSettingView.mOnCallGroupNameLayout = null;
            groupSettingView.mOnCallDescRegion = null;
            groupSettingView.mOnCallDescTv = null;
            groupSettingView.mOnCallDescMoreArrow = null;
            groupSettingView.mGroupBotRegion = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }

    public GroupSettingView_ViewBinding(GroupSettingView groupSettingView, View view) {
        this.f90192a = groupSettingView;
        groupSettingView.mScrollView = (ScrollView) Utils.findRequiredViewAsType(view, R.id.scrollview, "field 'mScrollView'", ScrollView.class);
        groupSettingView.mTitleBar = (CommonTitleBar) Utils.findRequiredViewAsType(view, R.id.titlebar, "field 'mTitleBar'", CommonTitleBar.class);
        groupSettingView.mGroupMemberGv = (GridView) Utils.findRequiredViewAsType(view, R.id.gv_members, "field 'mGroupMemberGv'", GridView.class);
        groupSettingView.mGroupMemberLayout = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_group_members_region, "field 'mGroupMemberLayout'", LinearLayout.class);
        groupSettingView.mGroupMemberTitleTv = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_group_member_title, "field 'mGroupMemberTitleTv'", TextView.class);
        groupSettingView.mChatterAllCountTV = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_member_count, "field 'mChatterAllCountTV'", TextView.class);
        groupSettingView.mGroupSettingLayout = (RelativeLayout) Utils.findRequiredViewAsType(view, R.id.rl_group_setting_region, "field 'mGroupSettingLayout'", RelativeLayout.class);
        groupSettingView.mGroupSettingTv = (TextView) Utils.findRequiredViewAsType(view, R.id.group_setting_tv, "field 'mGroupSettingTv'", TextView.class);
        groupSettingView.mStartPositionLayout = Utils.findRequiredView(view, R.id.rl_chat_setting_region, "field 'mStartPositionLayout'");
        groupSettingView.mStartPositionTV = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_chat_setting, "field 'mStartPositionTV'", TextView.class);
        groupSettingView.mChatSettingTitlt = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_chat_setting_title, "field 'mChatSettingTitlt'", TextView.class);
        groupSettingView.mMuteLayoutWrapper = Utils.findRequiredView(view, R.id.mute_layout_wrapper, "field 'mMuteLayoutWrapper'");
        groupSettingView.mMuteLayoutBottomDivider = (TextView) Utils.findRequiredViewAsType(view, R.id.mute_layout_bottom_divider, "field 'mMuteLayoutBottomDivider'", TextView.class);
        groupSettingView.mMsgNotifyTV = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_message_notify, "field 'mMsgNotifyTV'", TextView.class);
        groupSettingView.mSwitchButton = (LKUISwitchButton) Utils.findRequiredViewAsType(view, R.id.sb_notification_switch, "field 'mSwitchButton'", LKUISwitchButton.class);
        groupSettingView.mMsgNotifySubtitle = Utils.findRequiredView(view, R.id.tv_message_notify_subtitle, "field 'mMsgNotifySubtitle'");
        groupSettingView.mAutoTranslateLayout = (RelativeLayout) Utils.findRequiredViewAsType(view, R.id.rl_auto_translate_region, "field 'mAutoTranslateLayout'", RelativeLayout.class);
        groupSettingView.mAutoTranslateTipsTv = (TextView) Utils.findRequiredViewAsType(view, R.id.auto_translate_tips, "field 'mAutoTranslateTipsTv'", TextView.class);
        groupSettingView.mAutoTranslateSwitch = (LKUISwitchButton) Utils.findRequiredViewAsType(view, R.id.sb_auto_translate_switch, "field 'mAutoTranslateSwitch'", LKUISwitchButton.class);
        groupSettingView.mAutoTranslateLabelTv = (TextView) Utils.findRequiredViewAsType(view, R.id.auto_translate_label, "field 'mAutoTranslateLabelTv'", TextView.class);
        groupSettingView.mNicknameLayout = Utils.findRequiredView(view, R.id.nickname_layout, "field 'mNicknameLayout'");
        groupSettingView.mNickTipTv = (TextView) Utils.findRequiredViewAsType(view, R.id.nickname_tip, "field 'mNickTipTv'", TextView.class);
        groupSettingView.mNicknameTv = (TextView) Utils.findRequiredViewAsType(view, R.id.nickname_tv, "field 'mNicknameTv'", TextView.class);
        groupSettingView.mRedDot = Utils.findRequiredView(view, R.id.chat_apply_badge, "field 'mRedDot'");
        groupSettingView.mAddQuickSwitchLayout = (ViewGroup) Utils.findRequiredViewAsType(view, R.id.rl_add_quick_switch, "field 'mAddQuickSwitchLayout'", ViewGroup.class);
        groupSettingView.mAddQuickSwitchTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_quickswitcher_title, "field 'mAddQuickSwitchTitle'", TextView.class);
        groupSettingView.mAddQuickSwitchSubtitle = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_quickswitcher_subtitle, "field 'mAddQuickSwitchSubtitle'", TextView.class);
        groupSettingView.mShortcutSwitchButton = (LKUISwitchButton) Utils.findRequiredViewAsType(view, R.id.sb_quickswitcher_switch, "field 'mShortcutSwitchButton'", LKUISwitchButton.class);
        groupSettingView.mChatBoxLayout = (RelativeLayout) Utils.findRequiredViewAsType(view, R.id.chat_box_layout_wrapper, "field 'mChatBoxLayout'", RelativeLayout.class);
        groupSettingView.mMoveToBoxSwitchButton = (LKUISwitchButton) Utils.findRequiredViewAsType(view, R.id.sb_move_to_box_switch, "field 'mMoveToBoxSwitchButton'", LKUISwitchButton.class);
        groupSettingView.mMoveToBoxTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_move_to_box_title, "field 'mMoveToBoxTitle'", TextView.class);
        groupSettingView.mMoveToBoxSubtitle = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_move_to_box_subtitle, "field 'mMoveToBoxSubtitle'", TextView.class);
        groupSettingView.mThreadFavoriteLayout = (ViewGroup) Utils.findRequiredViewAsType(view, R.id.thread_favorite_layout, "field 'mThreadFavoriteLayout'", ViewGroup.class);
        groupSettingView.mThreadSubscribeSwitch = (LKUISwitchButton) Utils.findRequiredViewAsType(view, R.id.sb_move_to_favorite_switch, "field 'mThreadSubscribeSwitch'", LKUISwitchButton.class);
        groupSettingView.mTvSubscribeThreadTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_favorite_thread_title, "field 'mTvSubscribeThreadTitle'", TextView.class);
        groupSettingView.mTvSubscribeThreadSubTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_favorite_thread_subtitle, "field 'mTvSubscribeThreadSubTitle'", TextView.class);
        groupSettingView.mIvSubscribeThreadDesc = (ImageView) Utils.findRequiredViewAsType(view, R.id.iv_favorite_thread_desc, "field 'mIvSubscribeThreadDesc'", ImageView.class);
        groupSettingView.mChatFunctionView = (ChatFunctionView) Utils.findRequiredViewAsType(view, R.id.chat_function_view, "field 'mChatFunctionView'", ChatFunctionView.class);
        groupSettingView.mOnCallRegionNew = (ViewGroup) Utils.findRequiredViewAsType(view, R.id.ll_oncall_region_new, "field 'mOnCallRegionNew'", ViewGroup.class);
        groupSettingView.mOnCallDescTvNew = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_oncall_describe_new, "field 'mOnCallDescTvNew'", TextView.class);
        groupSettingView.mOnCallDescMoreArrowNew = (ImageView) Utils.findRequiredViewAsType(view, R.id.iv_right_arrow_oncall_new, "field 'mOnCallDescMoreArrowNew'", ImageView.class);
        groupSettingView.mOnCallAboutTvNew = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_oncall_about_new, "field 'mOnCallAboutTvNew'", TextView.class);
        groupSettingView.mLeaveGroupTV = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_leave_group, "field 'mLeaveGroupTV'", TextView.class);
        groupSettingView.mReportBtn = Utils.findRequiredView(view, R.id.report_btn, "field 'mReportBtn'");
        groupSettingView.mSecretChatTransferTo = (RelativeLayout) Utils.findOptionalViewAsType(view, R.id.secret_chat_transfer_to, "field 'mSecretChatTransferTo'", RelativeLayout.class);
        groupSettingView.mDissolveGroupBtn = (TextView) Utils.findOptionalViewAsType(view, R.id.dissolve_group_button, "field 'mDissolveGroupBtn'", TextView.class);
        groupSettingView.mSearchChatLayout = view.findViewById(R.id.setting_search_layout);
        groupSettingView.mSearchChatTypeRv = (RecyclerView) Utils.findOptionalViewAsType(view, R.id.search_chat_type, "field 'mSearchChatTypeRv'", RecyclerView.class);
        groupSettingView.mSearchChatTitleLayout = view.findViewById(R.id.search_chat_title_layout);
        groupSettingView.mChatFunctionLayout = view.findViewById(R.id.setting_function_layout);
        groupSettingView.mChatInfoRedesignView = view.findViewById(R.id.chat_info_view_redesign);
        groupSettingView.mGroupAvatarRedesign = (LKUIRoundedImageView2) Utils.findOptionalViewAsType(view, R.id.setting_group_avatar, "field 'mGroupAvatarRedesign'", LKUIRoundedImageView2.class);
        groupSettingView.mGroupNameTvRedesign = (TextView) Utils.findOptionalViewAsType(view, R.id.group_name_tv, "field 'mGroupNameTvRedesign'", TextView.class);
        groupSettingView.mGroupNameIconView = (ImageView) Utils.findOptionalViewAsType(view, R.id.group_name_iv, "field 'mGroupNameIconView'", ImageView.class);
        groupSettingView.mTeamNameTv = (TextView) Utils.findRequiredViewAsType(view, R.id.team_name_tv, "field 'mTeamNameTv'", TextView.class);
        groupSettingView.mGroupQrcodeIvRedesign = (ImageView) Utils.findOptionalViewAsType(view, R.id.group_qrcode_iv, "field 'mGroupQrcodeIvRedesign'", ImageView.class);
        groupSettingView.mGroupInfoArrow = (ImageView) Utils.findOptionalViewAsType(view, R.id.group_info_arrow, "field 'mGroupInfoArrow'", ImageView.class);
        groupSettingView.mGroupAvatarSecretIv = (ImageView) Utils.findOptionalViewAsType(view, R.id.group_avatar_secret_iv, "field 'mGroupAvatarSecretIv'", ImageView.class);
        groupSettingView.mGroupRegionDivider = Utils.findRequiredView(view, R.id.group_title_divider, "field 'mGroupRegionDivider'");
        groupSettingView.mOnCallHeaderIv = (LKUIRoundedImageView2) Utils.findRequiredViewAsType(view, R.id.iv_oncall_group_pic, "field 'mOnCallHeaderIv'", LKUIRoundedImageView2.class);
        groupSettingView.mOnCallGroupNameTv = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_oncall_group_name, "field 'mOnCallGroupNameTv'", TextView.class);
        groupSettingView.mOnCallGroupNameLayout = (RelativeLayout) Utils.findRequiredViewAsType(view, R.id.rl_oncall_group_name_region, "field 'mOnCallGroupNameLayout'", RelativeLayout.class);
        groupSettingView.mOnCallDescRegion = (RelativeLayout) Utils.findRequiredViewAsType(view, R.id.oncall_desc_region, "field 'mOnCallDescRegion'", RelativeLayout.class);
        groupSettingView.mOnCallDescTv = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_oncall_desc, "field 'mOnCallDescTv'", TextView.class);
        groupSettingView.mOnCallDescMoreArrow = (ImageView) Utils.findRequiredViewAsType(view, R.id.iv_arrow_desc_more, "field 'mOnCallDescMoreArrow'", ImageView.class);
        groupSettingView.mGroupBotRegion = view.findViewById(R.id.rl_group_bot_region);
    }
}